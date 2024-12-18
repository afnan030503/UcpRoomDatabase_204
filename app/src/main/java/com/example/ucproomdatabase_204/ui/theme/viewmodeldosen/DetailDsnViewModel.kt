package com.example.ucproomdatabase_204.ui.theme.viewmodeldosen

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.data.entity.Dosen
import com.example.ucproomdatabase_204.repository.RepositoryDsn
import com.example.ucproomdatabase_204.ui.theme.navigation.DestinasiDetailDosen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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

class DetailDsnViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDsn: RepositoryDsn,
): ViewModel(){
    private val _nidn: String = checkNotNull(savedStateHandle[DestinasiDetailDosen.NIDN])

    val detailUistate: StateFlow<DetailUistate> = repositoryDsn.getDsn(_nidn)
        .filterNotNull()
        .map {
            DetailUistate(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUistate(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUistate(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUistate(
                isLoading = true,
            ),
        )
}