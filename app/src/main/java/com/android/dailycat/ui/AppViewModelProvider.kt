package com.android.dailycat.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.dailycat.DailyCatApplication
import com.android.dailycat.ui.screens.AppViewModel
import com.android.dailycat.ui.screens.appScreen.favorites.FavoriteViewModel

object AppViewModelProvider {
    private var Instance: AppViewModel? = null

    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            if (Instance == null) {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as DailyCatApplication
                Instance = AppViewModel(
                    application.container.catPostRepository,
                    application.container.favoriteRepository
                )
            }
            Instance!!
        }

        initializer {
            FavoriteViewModel(dailyCatApplication().container.favoriteRepository)
        }


    }

    fun CreationExtras.dailyCatApplication(): DailyCatApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DailyCatApplication)

}