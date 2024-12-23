package com.example.ucproomdatabase_204.ui.theme.view.dosen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.KeyEventDispatcher.Component
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase_204.data.entity.Dosen
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
    detailUistate.isUiEventEmpty -> {
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
fun ComponentDetailDsn(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun ItemDetailDsn(

    modifier: Modifier = Modifier,
    dosen: Dosen
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
            ComponentDetailDsn(judul = "NIDN", isinya = dosen.nidn)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailDsn(judul = "Nama", isinya = dosen.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailDsn(judul = "Jenis Kelamin", isinya = dosen.jeniskelamin)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}
