package com.example.ucproomdatabase_204.repository

import com.example.ucproomdatabase_204.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow


interface RepositoryMk {
    suspend fun insertMk(matakuliah: Matakuliah)

    fun getAllMk() : Flow<List<Matakuliah>>

    fun getMk(nidn: String) : Flow<Matakuliah>

    suspend fun deleteMk(matakuliah: Matakuliah)

    suspend fun updateMk(matakuliah: Matakuliah)
}