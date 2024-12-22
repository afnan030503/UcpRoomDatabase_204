package com.example.ucproomdatabase_204.ui.theme.view.dosen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.ui.theme.costumwidget.cmtopBar
import com.example.ucproomdatabase_204.ui.theme.viewmodeldosen.DetailDsnViewModel

@Composable
fun DetailDsnView(
    modifier: Modifier = Modifier,
    viewModel: DetailDsnViewModel = viewModel(factory = PenyediaDsnViewModel.Factory),
    onBack: () -> Unit = {},
){
   Scaffold(
       topBar = {
           cmtopBar(
               judul = " Detail Dosen",
               showBackButton = true,
               onBack = onBack,
               modifier = Modifier
           )
       },
           ) { innerPadding ->
       val detailUistate by viewModel.detailUistate.collectAsState()

       BodyDetailDsn(
           modifier = Modifier.padding(innerPadding),
           detailUistate = detailUistate,
       )
   }

}