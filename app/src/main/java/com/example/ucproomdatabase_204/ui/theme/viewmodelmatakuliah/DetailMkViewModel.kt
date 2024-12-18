package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.repository.RepositoryMk
import com.example.ucproomdatabase_204.ui.theme.navigation.DestinasiDetailMatakuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class DetailUiMState(
    val detailUiMEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get()= detailUiMEvent == MatakuliahEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailUiMEvent != MatakuliahEvent()
}


class DetailMkViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
): ViewModel() {
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetailMatakuliah.KODE])

    val detailUiMState: StateFlow<DetailUiMState> = repositoryMk.getMk(_kode)
        .filterNotNull()
        .map {
            DetailUiMState(
                detailUiMEvent = it.toDetailMatakuliah(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiMState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
            DetailUiMState(
                isLoading = false,
                isError = true,
                errorMessage = it.message ?: "terjadi kesalahan",
            )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiMState(
                isLoading = true,
            ),
        )
    fun deleteMk(){
        detailUiMState.value.detailUiMEvent.toMatakuliahEntity().let{
            viewModelScope.launch {
                repositoryMk.deleteMk(it)
            }
        }
    }
}

fun Matakuliah.toDetailUiMEvent(): MatakuliahEvent{
    return MatakuliahEvent(
        kode = kode,
        nama_mk = nama_mk,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenpengampu = dosenpengampu
    )
}