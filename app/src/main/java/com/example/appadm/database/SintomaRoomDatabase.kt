package com.example.appadm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appadm.database.daos.SintomaDao
import com.example.appadm.database.models.SintomaControle
import com.example.appadm.models.Sintoma
import kotlinx.coroutines.CoroutineScope

@Database(entities = [SintomaControle::class], version = 1, exportSchema = false)
abstract class SintomaRoomDatabase : RoomDatabase() {

    abstract fun sintomaDao(): SintomaDao

    companion object {

        @Volatile
        private var INSTANCE: SintomaRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): SintomaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SintomaRoomDatabase::class.java,
                    "sintoma_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }



}