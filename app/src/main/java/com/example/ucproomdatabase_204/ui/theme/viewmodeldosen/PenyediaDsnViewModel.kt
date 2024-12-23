package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import android.text.Editable.Factory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaDsnViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                Krs
            )
        }
    }
}