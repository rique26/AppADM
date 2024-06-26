package com.example.appadm.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class FormLoginViewModel : ViewModel() {
    private val loginSuccess = MutableLiveData<Boolean>()
    var mensagens = arrayOf("Preencha todos os campos", "Login realizado com sucesso")

    // Método para lidar com o login do usuário
    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            loginSuccess.setValue(false)
        }
        else{
            loginSuccess.setValue(true)
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("TAG", "usuario logado com sucesso")
                loginSuccess.setValue(true)
            }else{
                Log.d("TAG", "Login nao foi feito com sucesso")
                loginSuccess.setValue(false)
            }
        }
    }

    // Método para observar o sucesso do login
    fun observeLoginSuccess(): LiveData<Boolean> {
        return loginSuccess
    }




}