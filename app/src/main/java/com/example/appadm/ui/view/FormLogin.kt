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
    private val messages = arrayOf("Preencha todos os campos", "Login realizado com sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setupWindowInsets()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        // Atualizar a interface do usuário quando a atividade for retomada
        updateUI()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.formlogin) { view: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupClickListeners() {
        binding.buttonEntrar.setOnClickListener { view ->
            val email = binding.editEmail.text.toString()
            val password = binding.editSenha.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                showSnackbar(view, messages[0])
            } else {
                loginUser(view, email, password)
            }
        }

        binding.buttonCriarConta.setOnClickListener {
            startActivity(Intent(this, FormRegister::class.java))
        }
    }

    private fun loginUser(view: View, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showSnackbar(view, messages[1])
                    openControlScreen()
                } else {
                    val error = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "Usuário não encontrado."
                        is FirebaseAuthInvalidCredentialsException -> "Credenciais inválidas."
                        else -> "Erro ao realizar login."
                    }
                    showSnackbar(view, error)
                }
            }
    }

    private fun openControlScreen() {
        startActivity(Intent(this, ControlHomeScreen::class.java))
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.BLUE)
            .setTextColor(Color.BLACK)
            .show()
    }

    private fun updateUI() {
        // Atualizar qualquer parte da interface do usuário que precise ser renovada quando a atividade é retomada
        // Exemplo: limpar campos de texto
        binding.editEmail.text.clear()
        binding.editSenha.text.clear()
    }
}
