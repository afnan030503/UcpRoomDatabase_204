package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import com.example.ucproomdatabase_204.data.entity.Matakuliah


data class DetailUiMState(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get()= detailUiEvent == MatakuliahEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent()
}