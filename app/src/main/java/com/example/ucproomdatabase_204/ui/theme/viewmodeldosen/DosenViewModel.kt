package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

data class DsnUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snacBarMessage: String? = null,
)