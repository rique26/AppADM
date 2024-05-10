package com.example.appadm.ViewModel

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel


class FormLoginViewModel : ViewModel() {
    private val loginSuccess = MutableLiveData<Boolean>()

    // Método para lidar com o login do usuário
    fun loginUser(email: String, password: String) {
        // Adicione aqui a lógica para autenticar o usuário
        // Exemplo simplificado:
        if (email == "teste@gmail.com" && password == "123456") {
            loginSuccess.setValue(true)
        } else {
            loginSuccess.setValue(false)
        }
    }

    // Método para observar o sucesso do login
    fun observeLoginSuccess(): LiveData<Boolean> {
        return loginSuccess
    }
}