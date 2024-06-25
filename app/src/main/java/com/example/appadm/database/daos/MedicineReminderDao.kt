package com.example.appadm.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appadm.database.models.MedicineReminder

@Dao
interface MedicineReminderDao {

    @Insert
    fun insert(medicamentoConfirmacao: MedicineReminder)

    @Query("SELECT * FROM medicamento_table")
    fun getMedicamento(): List<MedicineReminder>

    @Query("DELETE FROM medicamento_table")
    suspend fun deleteAllMedicamentos()

}