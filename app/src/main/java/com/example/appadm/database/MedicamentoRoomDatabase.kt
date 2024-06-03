package com.example.appadm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appadm.database.daos.MedicamentoDao
import com.example.appadm.database.daos.SintomaDao
import com.example.appadm.database.models.MedicamentoAgenda
import com.example.appadm.database.models.SintomaControle
import com.example.appadm.models.Sintoma
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MedicamentoAgenda::class], version = 1, exportSchema = false)
abstract class MedicamentoRoomDatabase : RoomDatabase() {

    abstract fun medicamentoaDao(): MedicamentoDao

    companion object {

        @Volatile
        private var INSTANCE: MedicamentoRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): MedicamentoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicamentoRoomDatabase::class.java,
                    "medicamento_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }



}