package com.example.appadm.repositories

import androidx.annotation.WorkerThread
import com.example.appadm.database.daos.SintomaDao
import com.example.appadm.database.models.SintomaControle
import com.example.appadm.models.Sintoma
import kotlinx.coroutines.flow.Flow

////interface SintomaRepository (private val sintomaDao: SintomaDao){
//    //val allSintomas: Flow<List<SintomaControle>> = sintomaDao.getAlphabetizedSintomas()
//
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(sintoma: SintomaControle) {
//        sintomaDao.insert(sintoma)
//    }
//
//    //fun createSintoma(registrationViewParams)
//}