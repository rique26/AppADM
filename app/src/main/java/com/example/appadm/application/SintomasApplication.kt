package com.example.appadm.application

import android.app.Application
import com.example.appadm.database.SintomaRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SintomasApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { SintomaRoomDatabase.getDatabase(applicationScope,this) }
    //val repository by lazy { SintomaRepository(database.sintomaDao()) }

}