package com.example.appadm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appadm.database.daos.MedicineDao
import com.example.appadm.database.models.AllMedicines
import kotlinx.coroutines.CoroutineScope

@Database(entities = [AllMedicines::class], version = 1, exportSchema = false)
abstract class AllMedicinesRoomDatabase : RoomDatabase() {

    abstract fun medicamentoaDao(): MedicineDao

    companion object {

        @Volatile
        private var INSTANCE: AllMedicinesRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): AllMedicinesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllMedicinesRoomDatabase::class.java,
                    "AllMedicines_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }



}