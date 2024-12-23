package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import android.text.Editable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase_204.ui.theme.akdkrsApp

object PenyediaDsnViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                akdkrsApp().containerApp.repositoryDsn
            )
        }
        initializer {
            DetailDsnViewModel(
                createSavedStateHandle(),
                akdkrsApp().containerApp.repositoryDsn,
            )
        }
    }
}

fun CreationExtras.akdkrsApp(): akdkrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as akdkrsApp)