package com.example.ucproomdatabase_204.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase_204.data.dao.DosenDao
import com.example.ucproomdatabase_204.data.dao.MatakuliahDao
import com.example.ucproomdatabase_204.data.entity.Dosen
import com.example.ucproomdatabase_204.data.entity.Matakuliah


@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class AkademikDatabase : RoomDatabase() {

    // Mendefinisikan fungsi DAO untuk masing-masing tabel
    abstract fun matakuliahDao(): MatakuliahDao
    abstract fun dosenDao(): DosenDao

    companion object {
        @Volatile
        private var Instance: AkademikDatabase? = null

        fun getDatabase(context: Context): AkademikDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AkademikDatabase::class.java, // Class database
                    "AkademikDatabase" // Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}