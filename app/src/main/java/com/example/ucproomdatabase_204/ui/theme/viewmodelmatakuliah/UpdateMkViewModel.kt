package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import androidx.compose.runtime.mutableStateOf
import com.example.ucproomdatabase_204.repository.RepositoryMk


class UpdateMkViewModel {
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
} : ViewModel() {

    var updateUiMState by mutableStateOf(MkUiState)
}