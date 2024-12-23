package com.example.ucproomdatabase_204.dependeciesinjection

import android.content.Context
import com.example.ucproomdatabase_204.data.database.AkademikDatabase
import com.example.ucproomdatabase_204.data.database.KrsDatabase
import com.example.ucproomdatabase_204.repository.LocalRepositoryDsn
import com.example.ucproomdatabase_204.repository.LocalRepositoryMk
import com.example.ucproomdatabase_204.repository.RepositoryDsn
import com.example.ucproomdatabase_204.repository.RepositoryMk

interface InterfaceContainerApp {
    val repositoryDsn: RepositoryDsn
    val repositoryMk: RepositoryMk
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepositoryDsn(AkademikDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(KrsDatabase.getDatabase(context).matakuliahDao())
    }

}


