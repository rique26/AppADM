package com.example.appadm.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appadm.database.models.AllMedicines


@Dao
interface MedicineDao {

    @Insert
    fun insert(medicamento : AllMedicines)

    @Query("SELECT * FROM medicamento_table")
    fun getMedicamento(): List<AllMedicines>

    @Query("DELETE FROM medicamento_table")
    suspend fun deleteAllMedicamentos()


}
