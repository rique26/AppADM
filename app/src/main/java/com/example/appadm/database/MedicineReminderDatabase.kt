package com.example.appadm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appadm.database.daos.MedicineReminderDao
import com.example.appadm.database.models.MedicineReminder

@Database(entities = [MedicineReminder::class], version = 1, exportSchema = false)
abstract class MedicineReminderDatabase : RoomDatabase() {

    abstract fun medicamentoConfirmacaoDao(): MedicineReminderDao

    companion object {

        private const val DATABASE_NAME: String = "MedicineReminderDatabase"

        @Volatile
        private var INSTANCE: MedicineReminderDatabase? = null

        fun getInstance(context: Context): MedicineReminderDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MedicineReminderDatabase::class.java, DATABASE_NAME
            ).build()
    }



}