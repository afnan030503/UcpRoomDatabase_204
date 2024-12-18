package com.example.ucproomdatabase_204.ui.theme.navigation

interface AlamatNavigasi{
    val route: String
}
object DestinasiHome : AlamatNavigasi{
    override val route = "Home"
}
object DestinasiDetailDosen : AlamatNavigasi{
    override val route = "Detail"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}
object DestinasiUpdateDosen : AlamatNavigasi{
    override val route = "update"
    const val NIDN = "kode"
    val routesWithArg = "$route/{$NIDN}"
}
object DestinasiDetailMatakuliah : AlamatNavigasi{
    override val route = "Detail"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}
object DestinasiUpdateMatakuliah : AlamatNavigasi{
    override val route = "update"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}
