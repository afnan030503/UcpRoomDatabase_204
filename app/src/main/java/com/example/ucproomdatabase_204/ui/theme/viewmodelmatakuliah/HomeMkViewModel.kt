package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.repository.RepositoryDsn
import com.example.ucproomdatabase_204.repository.RepositoryMk
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.time.delay

class HomeMkViewModel(
    private val repositoryMk: RepositoryMk
): ViewModel(){
    val homeUiMState: StateFlow<HomeUiMState> = repositoryMk.getAllMk()
        .filterNotNull()
        .map {
            HomeUiMState(
                listMk = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiMState(isLoading = true))
            kotlinx.coroutines.delay(900)
        }
        .catch {
            emit(HomeUiMState(
                isLoading = false,
                isError = true,
                errorMessage = it.message ?: "terjadi kesalahan"
            )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiMState(
                isLoading = true,
            )
        )
}


data class HomeUiMState(
    val listMk: List<Matakuliah> = listOf(),
    val isLoading: Boolean= false,
    val isError: Boolean= false,
    val errorMessage: String =""
)
