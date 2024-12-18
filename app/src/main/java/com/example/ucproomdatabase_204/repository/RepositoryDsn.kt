package com.example.ucproomdatabase_204.repository

import com.example.ucproomdatabase_204.data.entity.Dosen
import kotlinx.coroutines.flow.Flow


interface RepositoryDsn {
    suspend fun insertDsn(dosen: Dosen)

    fun getAllDsn() : Flow<List<Dosen>>

    fun getDsn(nidn: String) : Flow<Dosen>
}