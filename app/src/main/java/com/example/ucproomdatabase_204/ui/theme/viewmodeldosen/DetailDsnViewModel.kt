package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import com.example.ucproomdatabase_204.data.entity.Dosen

data class DetailUistate(
    val detailUiEvent: DosenEvent = Dosenevent(),
    val isLoading: Boolean= false,
    val isError: Boolean= false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

fun Dosen.toDetailUiEvent(): DosenEvent{
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jeniskelamin = jeniskelamin
    )
}