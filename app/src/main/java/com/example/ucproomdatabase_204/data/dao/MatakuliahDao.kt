package com.example.ucproomdatabase_204.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdatabase_204.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow


@Dao
interface MatakuliahDao {
    @Insert
    suspend fun insertMatakuliah(matakuliah: Matakuliah)

    @Query("SELECT * FROM matakuliah ORDER BY nama_mk ASC")
    fun getAllMatakuliah() : Flow<List<Matakuliah>>

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatakuliah(kode: String) : Flow<Matakuliah>

    @Delete
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)

    @Update
    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}