package com.android.dailycat.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class AppViewModel(/*private val loadSlipsRepository: LoadSlipsRepository*/) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyCatOverviewState( ))
    val uiState : StateFlow<DailyCatOverviewState> = _uiState.asStateFlow()

    var dailyCatApiState : DailyCatApiState by mutableStateOf(DailyCatApiState.Loading)
        private set

    init {
        viewModelScope.launch {

        }
    }

}

data class DailyCatOverviewState(
    val image : String = ""
)

sealed interface DailyCatApiState{
    object Success: DailyCatApiState
    object Error: DailyCatApiState
    object Loading : DailyCatApiState
}


