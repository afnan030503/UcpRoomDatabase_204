package com.example.ucproomdatabase_204.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucproomdatabase_204.ui.theme.view.dosen.DestinasiInsert
import com.example.ucproomdatabase_204.ui.theme.view.dosen.DetailDsnView
import com.example.ucproomdatabase_204.ui.theme.view.dosen.HomeDsnView
import com.example.ucproomdatabase_204.ui.theme.view.dosen.InsertDsnView
import com.example.ucproomdatabase_204.ui.theme.view.matakuliah.DetailMkView
import com.example.ucproomdatabase_204.ui.theme.view.matakuliah.HomeMkView
import com.example.ucproomdatabase_204.ui.theme.view.matakuliah.InsertMkView
import com.example.ucproomdatabase_204.ui.theme.view.matakuliah.UpdateMkView

@Preview@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHomeMk.route) {
        composable(route = DestinasiHomeMk.route) {
            HomeMkView(
                onMDetailClick = { kode ->
                    // Navigasi ke halaman detail dosen
                    navController.navigate("${DestinasiDetailMatakuliah.route}/$kode")
                    println(
                        "PengelolaHalaman: kode = $kode"
                    )
                },

                onAddMk = {
                    navController.navigate(DestinasiInsert.route)
                },

                modifier = modifier
            )
        }

        // Halaman HomeMkView untuk Matakuliah
        composable(
            route = DestinasiHomeMk.route
        ) {
           InsertMkView(
               onBack = {
                   navController.popBackStack()
               },
               onNavigate = {
                   navController.popBackStack()
               },
               modifier = modifier,
           )

        }
        composable(
            DestinasiDetailMatakuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMatakuliah.KODE){
                    type = NavType.StringType
                }

            )
        ){
            val kode = it.arguments?.getString(DestinasiDetailMatakuliah.KODE)

            kode?.let { kode ->
                DetailMkView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.popBackStack()
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )

            }
        }
        composable(
        DestinasiUpdateMatakuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMatakuliah.KODE){
                    type = NavType.StringType
                }
            )
        ){
            UpdateMkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }

}
