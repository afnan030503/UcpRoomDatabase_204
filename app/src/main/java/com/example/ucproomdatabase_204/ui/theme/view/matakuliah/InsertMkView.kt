package com.example.ucproomdatabase_204.ui.theme.view.matakuliah

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.ui.theme.costumwidget.cmtopBar
import com.example.ucproomdatabase_204.ui.theme.navigation.AlamatNavigasi
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.FormErrorState
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.MatakuliahEvent
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.MatakuliahViewModel
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.MkUiMState
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.PenyediaMkViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBodyUMk(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MkUiMState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormUMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}
object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_Mk"
}
@Composable
fun InsertMkView(
        onBack: () -> Unit,
        onNavigate: () -> Unit,
        modifier: Modifier = Modifier,
        viewModel: MatakuliahViewModel = viewModel(factory = PenyediaMkViewModel.Factory)
    ) {
    val uiState = viewModel.uiMState // Ambil UI state dari view model
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackbarmessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message) // Tampilkan snackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            cmtopBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah"
            )
            // Isi body
            InsertBodyUMk(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) // Update state di view model
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData() // Simpan data
                    }
                    onNavigate()
                }
            )

        }
    }
}



@Composable
fun FormUMatakuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val jenisList = listOf("Wajib", "Pilihan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("Kode") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan Kode") },
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama_mk,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama_mk = it))
            },
            label = { Text("Nama Matakuliah") },
            isError = errorState.nama_mk != null,
            placeholder = { Text("Masukkan Nama Matakuliah") },
        )
        Text(
            text = errorState.nama_mk ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("SKS") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("Semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan Semester") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Matakuliah")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisList.forEach { jenis ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis))
                        },
                    )
                    Text(text = jenis)
                }
            }
        }
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )
    }


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.dosenpengampu,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(dosenpengampu = it))
            },
            label = { Text("Dosen Pengampu") },
            isError = errorState.dosenpengampu != null,
            placeholder = { Text("Masukkan Dosen Pengampu") },
        )
        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red)
    }


