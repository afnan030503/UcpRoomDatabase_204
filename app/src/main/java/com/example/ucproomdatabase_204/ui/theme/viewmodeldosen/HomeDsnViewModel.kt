package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_204.data.entity.Dosen
import com.example.ucproomdatabase_204.repository.RepositoryDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDsnViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel(){
    val homeUiState: StateFlow<HomeUiState> = repositoryDsn.getAllDsn()
        .filterNotNull()
        .map {
            HomeUiState(
                listDsn = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(HomeUiState(
                isLoading = false,
                iserror = true,
                errorMessage = it.message ?: "terjadi kesalahan"
            )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}


data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean= false,
    val iserror: Boolean= false,
    val errorMessage: String = ""
)