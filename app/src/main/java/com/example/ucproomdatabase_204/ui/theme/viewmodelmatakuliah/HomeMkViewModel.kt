package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import com.example.ucproomdatabase_204.data.entity.Matakuliah

data class HomeUiState(
    val listMk: List<Matakuliah> = listOf(),
    val isLoading: Boolean= false,
    val isError: Boolean= false,
    val errorMessage: String =""
)
