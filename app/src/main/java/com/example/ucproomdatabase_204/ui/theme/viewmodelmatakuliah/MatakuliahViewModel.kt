package com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.repository.RepositoryMk
import kotlinx.coroutines.launch


class MatakuliahViewModel(private val repositoryMk: RepositoryMk): ViewModel(){
    var uiMState by mutableStateOf(MkUiMState())

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        uiMState = uiMState.copy(
            matakuliahEvent = matakuliahEvent,
        )

    }
    private fun validateFields(): Boolean {
        val event = uiMState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else " Kode tidak boleh kosong",
            nama_mk = if (event.nama_mk.isNotEmpty()) null else " Nama Matakuliah tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else " Sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else " Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else " Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else " Dosen Pengampu tidak boleh kosong"
        )
        uiMState = uiMState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiMState.matakuliahEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMk.insertMk(currentEvent.toMatakuliahEntity())
                    uiMState = uiMState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiMState = uiMState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            uiMState = uiMState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiMState= uiMState.copy(
            snackBarMessage = null
        )
    }
}


data class MkUiMState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama_mk: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenpengampu: String? = null
) {
    fun isValid(): Boolean {
        return kode == null && nama_mk == null && sks == null && semester == null && jenis == null && dosenpengampu == null
    }
}
data class MatakuliahEvent(
    val kode: String = "",
    val nama_mk: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenpengampu: String = ""
)

fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama_mk = nama_mk,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu
)