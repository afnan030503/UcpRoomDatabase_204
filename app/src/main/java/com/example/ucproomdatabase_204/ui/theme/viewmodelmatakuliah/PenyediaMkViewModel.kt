package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase_204.ui.theme.akdkrsApp

object PenyediaMkViewModel{
    val Factory = viewModelFactory {
        initializer {
            MatakuliahViewModel(
                akdkrsApp().containerApp.repositoryMk
            )
        }
        initializer {
            HomeMkViewModel(
                akdkrsApp().containerApp.repositoryMk
            )
        }
        initializer {
            DetailMkViewModel(
                createSavedStateHandle(),
                akdkrsApp().containerApp.repositoryMk,
            )
        }
        initializer {
            UpdateMkViewModel(
                createSavedStateHandle(),
                akdkrsApp().containerApp.repositoryMk,
            )
        }
    }

}



fun CreationExtras.akdkrsApp(): akdkrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as akdkrsApp)
