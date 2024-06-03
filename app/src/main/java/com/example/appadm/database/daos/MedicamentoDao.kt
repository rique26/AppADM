package com.example.appadm.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appadm.database.models.MedicamentoAgenda
import com.example.appadm.database.models.SintomaControle
import com.example.appadm.models.Sintoma
import kotlinx.coroutines.flow.Flow


@Dao
interface MedicamentoDao {

    @Insert
    fun insert(medicamento : MedicamentoAgenda)

    @Query("SELECT * FROM medicamento_table")
    fun getMedicamento(): List<MedicamentoAgenda>


}
