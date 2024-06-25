package com.example.appadm.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicamento_table")
data class MedicineReminder(
    @ColumnInfo(name = "nome_medicamento") val nome_medicamento: String,
    @ColumnInfo(name = "horario1") val horario1: String,
    @ColumnInfo(name = "horario2") val horario2: String,
    @ColumnInfo(name = "horario3") val horario3: String,
    @ColumnInfo(name = "horario4") val horario4: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}