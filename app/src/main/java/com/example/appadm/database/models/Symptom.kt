package com.example.appadm.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sintoma_table")
data class Symptom(
    val disposicao: String,
    val horario: String,
    val sintoma: String,
    val dia: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


