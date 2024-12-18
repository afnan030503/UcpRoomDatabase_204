package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import com.example.ucproomdatabase_204.data.entity.Dosen
import com.example.ucproomdatabase_204.repository.RepositoryDsn

class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
)
data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean= false,
    val iserror: Boolean= false,
    val errorMessage: String = ""
)