package com.example.appadm.repositories

import com.example.appadm.rest.RetrofitService

class MedicamentoRepository constructor(private val retrofitService: RetrofitService){
    fun getAllMedicamentos() = retrofitService.getAllMedicamentos()
}