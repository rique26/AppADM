package com.example.appadm.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicamento_table")
data class AllMedicines(
    val nome_medicamento: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

