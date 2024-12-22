package com.example.ucproomdatabase_204.ui.theme.view.dosen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.ui.theme.viewmodeldosen.DetailDsnViewModel

@Composable
fun DetailDsnView(
    modifier: Modifier = Modifier,
    viewModel: DetailDsnViewModel = viewModel(factory = PenyediaDsnViewModel.Factory),
    onBack: () -> Unit = {},
){
    
}