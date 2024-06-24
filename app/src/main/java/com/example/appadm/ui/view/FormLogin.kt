package com.example.appadm.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appadm.databinding.ActivityFormLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding
    private val mensagens = arrayOf("Preencha todos os campos", "Login realizado com sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.formlogin) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonEntrar.setOnClickListener { v ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            if (email.isEmpty() || senha.isEmpty()) {
                showSnackbar(v, mensagens[0])
            } else {
                loginUsuario(v, email, senha)
            }
        }

        binding.buttonCriarConta.setOnClickListener {
            startActivity(Intent(this, FormRegister::class.java))
        }
    }

    private fun loginUsuario(v: View, email: String, senha: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showSnackbar(v, mensagens[1])
                    openTelaControle()
                } else {
                    val erro = when (val exception = task.exception) {
                        is FirebaseAuthInvalidUserException -> "Usuário não encontrado."
                        is FirebaseAuthInvalidCredentialsException -> "Credenciais inválidas."
                        else -> "Erro ao realizar login."
                    }
                    showSnackbar(v, erro)
                }
            }
    }

    private fun openTelaControle() {
        startActivity(Intent(this, ControlHomeScreen::class.java))
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.BLUE)
            .setTextColor(Color.BLACK)
            .show()
    }
}
