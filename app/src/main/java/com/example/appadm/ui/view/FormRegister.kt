package com.example.appadm.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appadm.R
import com.example.appadm.databinding.ActivityFormRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class FormRegister() : AppCompatActivity() {
    private lateinit var binding: ActivityFormRegisterBinding
    var mensagens = arrayOf("Preencha todos os campos", "Cadastro realizado com sucesso")
    var usuarioID: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        this.binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("LOG", "onCreate() TelaCadastroOficial")
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.telacadastrooficial),
            { v: View, insets: WindowInsetsCompat ->
                val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

    }

    override fun onStart() {
        super.onStart()
        Log.i("LOG", "onStart() TelaCadastroOficial")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LOG", "onResume() TelaCadastroOficial")
        binding.btContinuar1.setOnClickListener { v ->
            val nome = binding.editNome.getText().toString()
            val email = binding.editEmail.getText().toString()
            val senha = binding.editSenha.getText().toString()
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.BLUE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                CadastrarUsuario(v, email, senha)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("LOG", "onPause() TelaCadastroOficial")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LOG", "onStop() TelaCadastroOficial")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LOG", "onDestroy() TelaCadastroOficial")
    }

    private fun CadastrarUsuario(v: View, email: String, senha: String) {
        val email = binding.editEmail.getText().toString()
        val senha = binding.editSenha.getText().toString()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(
                OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        SalvarDadosUsuarios()
                        val snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.setTextColor(Color.BLACK)
                        snackbar.show()
                        startActivity(Intent(this, ControlScreen::class.java))
                    } else {
                        val erro: String
                        try {
                            throw (task.exception)!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            erro = "Digite uma senha com no minimo 6 digitos"
                        } catch (e: FirebaseAuthUserCollisionException) {
                            erro = "Esta conta ja foi cadastrada"
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            erro = "Email inválido"
                        } catch (e: Exception) {
                            erro = "Erro ao cadastrar usuario"
                        }
                        val snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.setTextColor(Color.BLACK)
                        snackbar.show()
                    }
                })
    }

    private fun SalvarDadosUsuarios() {
        val nome = binding.editNome.getText().toString()
        val db = FirebaseFirestore.getInstance()
        val usuarios: MutableMap<String, Any> = HashMap()
        usuarios["nome"] = nome
        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val documentReference = db.collection("Usuarios").document(
            usuarioID!!
        )
        documentReference.set(usuarios).addOnSuccessListener(object : OnSuccessListener<Void?> {
            override fun onSuccess(unused: Void?) {
                Log.d("db", "Sucesso ao salvar os dados")
            }
        })
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Log.d("db_error", "Erro ao salvar os dados$e")
                }
            })
    }


}