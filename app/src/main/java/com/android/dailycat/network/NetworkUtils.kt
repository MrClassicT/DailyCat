package com.android.dailycat.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

// SRC: https://medium.com/scalereal/observing-live-connectivity-status-in-jetpack-compose-way-f849ce8431c7 -> Only removed deprecated method.

/**
 * Represents the network connection state.
 */
sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}


/**
 * Extension property to get the current network connectivity state of the context.
 */
val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

/**
 * Retrieves the current network connectivity state.
 *
 * @param connectivityManager The ConnectivityManager instance to check the network status.
 * @return [ConnectionState] The current state of the network connection.
 */
private fun getCurrentConnectivityState(
    connectivityManager: ConnectivityManager
): ConnectionState {
    val connected = connectivityManager.activeNetwork?.describeContents() ?: false

    return if (connected != false) ConnectionState.Available else ConnectionState.Unavailable
}

/**
 * Observes network connectivity changes as a Flow.
 *
 * @return A Flow emitting [ConnectionState] reflecting the real-time network connectivity state.
 */
@ExperimentalCoroutinesApi
fun Context.observeConnectivityAsFlow() = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = NetworkCallback { connectionState -> trySend(connectionState) }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    // Set current state
    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    // Remove callback when not used
    awaitClose {
        // Remove listeners
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

/**
 * Creates a network callback for network connectivity changes.
 *
 * @param callback The function to invoke with the new [ConnectionState] when the connectivity state changes.
 * @return An instance of [ConnectivityManager.NetworkCallback] that calls the provided callback function.
 */
fun NetworkCallback(callback: (ConnectionState) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionState.Unavailable)
        }
    }
}

/**
 * Composable function to observe and react to network connectivity changes.
 *
 * @return A [State] object of type [ConnectionState] that represents the current network connectivity state.
 */
@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}