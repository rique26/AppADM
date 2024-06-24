package com.example.appadm.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appadm.database.models.Symptom


@Dao
interface SymptomDao {

    @Insert
    fun insert(sintoma : Symptom)

    @Query("SELECT * FROM sintoma_table")
    fun getSintoma(): List<Symptom>


}
