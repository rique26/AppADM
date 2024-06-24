package com.example.appadm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appadm.database.daos.SymptomDao
import com.example.appadm.database.models.Symptom
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Symptom::class], version = 1, exportSchema = false)
abstract class SymptomRoomDatabase : RoomDatabase() {

    abstract fun sintomaDao(): SymptomDao

    companion object {

        @Volatile
        private var INSTANCE: SymptomRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): SymptomRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SymptomRoomDatabase::class.java,
                    "Symptom_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }



}