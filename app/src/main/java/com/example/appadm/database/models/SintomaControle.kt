package com.example.appadm.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sintoma_table")
data class SintomaControle(
    val disposicao: String,
    val horario: String,
    val sintoma: String,
    val dia: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


