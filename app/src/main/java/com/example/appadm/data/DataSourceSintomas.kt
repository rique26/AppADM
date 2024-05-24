package com.example.appadm.data

import com.example.appadm.models.Medicamento
import com.example.appadm.models.Sintoma

class DataSourceSintomas {
    companion object {
        private val list = ArrayList<Sintoma>()

        fun createDataSet(dataSelecionada: String, horaSelecionada: String, sintomaAtual: String) {
            list.add(Sintoma("Sintoma", horaSelecionada, sintomaAtual, dataSelecionada))
        }

        fun getSintomas(): ArrayList<Sintoma> {
            return list
        }

    }

}