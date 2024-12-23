package com.example.ucproomdatabase_204.ui.theme.view.matakuliah

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.KeyEventDispatcher.Component
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import com.example.ucproomdatabase_204.ui.theme.costumwidget.cmtopBar
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.DetailMkViewModel
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.DetailUiMState

@Composable
fun DetailMkView(
    modifier: Modifier = Modifier,
    viewModel: DetailMkViewModel = viewModel(factory = PenyediaMkViewModel.Factory),
    onBack : () -> Unit = { },
    OnEditClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
    ){
    Scaffold(
        topBar = {
            cmtopBar(
                judul = "Detail MataKuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiMState.value.detailUiMEvent.kode)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Matakuliah",
                )
            }
        }
    ){ innerPadding ->
        val detailUiMState by viewModel.detailUiMState.collectAsState()

        BodyDetailMk(
            modifier = Modifier.padding(innerPadding),
            detailUiMState = detailUiMState,
            onDeleteClick = {
                viewModel.deleteMk()
                onDeleteClick()            }
        )

    }
}

@Composable
fun BodyDetailMk(
    modifier: Modifier = Modifier,
    detailUiMState: DetailUiMState = DetailUiMState(),
    onDeleteClick: () -> Unit = {}
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiMState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator() //tampil loading
            }
        }
        detailUiMState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { ItemDetailMk(
                matakuliah = detailUiMState.detailUiMEvent.toMatakuliahEntity(),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {

                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog (
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailUiMState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun ComponentDetailMk(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier =  modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontWeight = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ItemDetailMk(
    modifier: Modifier = Modifier,
    matakuliah: Matakuliah
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMk(judul = "KODE", isinya = matakuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "NAMAMK", isinya = matakuliah.nama_mk)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "SKS", isinya = matakuliah.sks)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "SEMESTER", isinya = matakuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "JENIS", isinya = matakuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMk(judul = "DOSENPENGAMPU", isinya = matakuliah.dosenpengampu)
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {/* Do Nothing */},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin menghapus data") },
        dismissButton =  {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }

    )
}