package com.example.ucproomdatabase_204.repository

import com.example.ucproomdatabase_204.data.dao.MatakuliahDao
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMk(
    private val matakuliahDao: MatakuliahDao
) : RepositoryMk {

    override suspend fun insertMk(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }
    override fun getAllMk(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }clas
    override fun getMk(nidn: String): Flow<Matakuliah>{
        return matakuliahDao.getMatakuliah(nidn)
    }
    override suspend fun updateMk(matakuliah: Matakuliah){
        matakuliahDao.updateMatakuliah(matakuliah)
    }
}