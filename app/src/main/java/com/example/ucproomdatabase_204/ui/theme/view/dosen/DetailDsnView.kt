package com.example.ucproomdatabase_204.ui.theme.view.dosen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.ui.theme.costumwidget.cmtopBar
import com.example.ucproomdatabase_204.ui.theme.viewmodeldosen.DetailDsnViewModel
import com.example.ucproomdatabase_204.ui.theme.viewmodeldosen.DetailUistate

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

@Composable
fun BodyDetailDsn(
    modifier: Modifier = Modifier,
    detailUistate: DetailUistate = DetailUistate(),
){
    when{
        detailUistate.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()//tampil loading
            }
        }
        detailUistate.isUiEventNotEmpty ->{
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailDsn(
                    dosen = detailUistate.detailUiEvent.toDosenEntity(),
                    modifier = Modifier
                )
            }
        }

    }
}