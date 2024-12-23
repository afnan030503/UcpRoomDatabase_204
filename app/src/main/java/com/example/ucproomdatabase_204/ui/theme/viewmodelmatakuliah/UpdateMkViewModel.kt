package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.repository.RepositoryMk
import com.example.ucproomdatabase_204.ui.theme.navigation.DestinasiUpdateMatakuliah
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UpdateMkViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
) : ViewModel() {

    var updateUiMState by mutableStateOf(MkUiMState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMatakuliah.KODE])

    init {
        viewModelScope.launch {
            updateUiMState = repositoryMk.getMk(_kode)
                .filterNotNull()
                .first()
                .toUIMStateMk()
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent){
        updateUiMState = updateUiMState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = updateUiMState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if(event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama_mk = if (event.nama_mk.isNotEmpty()) null else " Nama Matakuliah tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "Sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else " Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else " Dosen Pengampu tidak boleh kosong"
        )
        updateUiMState = updateUiMState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData(){
        val currentEvent = updateUiMState.matakuliahEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMk.updateMk(currentEvent.toMatakuliahEntity())
                    updateUiMState = updateUiMState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println(
                        "snackBarMessage diatur: ${
                            updateUiMState.snackBarMessage
                        }"
                    )
                }catch (e: Exception) {
                    updateUiMState = updateUiMState.copy(
                        snackBarMessage = "Data gagal diUpdate"
                    )
                }
            }
        }else{
            updateUiMState= updateUiMState.copy(
                snackBarMessage = "Data gagal diUpdate"
            )
        }
    }
    fun resetSnackBarMessage(){
        updateUiMState = updateUiMState.copy(snackBarMessage = null)
    }
}
fun Matakuliah.toUIMStateMk(): MkUiMState = MkUiMState(
    matakuliahEvent = this.toDetailUiMEvent(),
)