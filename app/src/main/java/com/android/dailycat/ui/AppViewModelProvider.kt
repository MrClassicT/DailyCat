package com.android.dailycat.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.dailycat.DailyCatApplication
import com.android.dailycat.ui.screens.AppViewModel

object AppViewModelProvider {
    private var Instance: AppViewModel? = null

    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            if (Instance == null) {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as DailyCatApplication
                Instance = AppViewModel(
                    catPostRepository = application.container.catPostRepository
                )
            }
            Instance!!
        }
    }

    fun CreationExtras.dailyCatApplication(): DailyCatApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DailyCatApplication)

}