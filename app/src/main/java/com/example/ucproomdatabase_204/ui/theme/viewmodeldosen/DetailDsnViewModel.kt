package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

data class DetailUistate(
    val detailUiEvent: DosenEvent = Dosenevent(),
    val isLoading: Boolean= false,
    val isError: Boolean= false,
    val errorMessage: String = ""
)