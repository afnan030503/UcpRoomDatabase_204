package com.example.ucproomdatabase_204.ui.theme.view.matakuliah

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.ui.theme.costumwidget.cmtopBar
import com.example.ucproomdatabase_204.ui.theme.viewmodelmatakuliah.DetailMkViewModel

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