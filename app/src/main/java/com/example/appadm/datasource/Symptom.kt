package com.example.appadm.datasource

import com.example.appadm.model.Symptom

class Symptom {
    companion object {
        private val list = ArrayList<Symptom>()

        fun createDataSet(dataSelecionada: String, horaSelecionada: String, sintomaAtual: String) {
            list.add(Symptom("Sintoma", horaSelecionada, sintomaAtual, dataSelecionada))
        }

        fun getSintomas(): ArrayList<Symptom> {
            return list
        }

    }

}