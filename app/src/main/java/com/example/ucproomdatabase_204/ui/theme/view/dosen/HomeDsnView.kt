package com.example.ucproomdatabase_204.ui.theme.view.dosen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.ui.theme.viewmodeldosen.HomeDsnViewModel

@Composable
fun HomeDsnView(
    viewModel: HomeDsnViewModel = viewModel(factory = PenyediaDsnViewModel.Factory),
    onAddDsn: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            cmTo
        }
    ) {  }
}