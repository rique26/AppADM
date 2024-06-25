package com.example.appadm.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appadm.R
import com.example.appadm.databinding.ActivityProfileHomeScreenBinding

class ProfileHomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityProfileHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSystemBarsPadding()

        binding.buttonSalvar.setOnClickListener {
            navigateToLogin()
        }
        initializeUIState()
    }

    override fun onResume() {
        super.onResume()
        setupButtonListeners()

    }

    private fun setupSystemBarsPadding() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupButtonListeners() {
        binding.imageButton2.setOnClickListener {
            selectControlTab()
        }

        binding.imageButton3.setOnClickListener {
            selectContactTab()
        }

        binding.imageButton1.setOnClickListener {
            selectScheduleTab()
        }

        binding.imageButton4.setOnClickListener {
            selectProfileTab()
        }
    }

    private fun initializeUIState() {
        binding.imageButton4.setImageResource(R.drawable.perfil_colorido)
        binding.txtImageButton4.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, FormLogin::class.java))
    }

    private fun selectControlTab() {
        binding.imageButton2.setImageResource(R.drawable.controle_azul)
        binding.imageButton1.setImageResource(R.drawable.schedule)
        binding.imageButton3.setImageResource(R.drawable.emergencia)

        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        binding.txtImageButton3.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))

        startActivity(Intent(this, ControlHomeScreen::class.java))
    }

    private fun selectContactTab() {
        binding.imageButton3.setImageResource(R.drawable.contato_azul)
        binding.imageButton1.setImageResource(R.drawable.schedule)
        binding.imageButton2.setImageResource(R.drawable.felicidade)

        binding.txtImageButton3.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
    }

    private fun selectScheduleTab() {
        binding.imageButton1.setImageResource(R.drawable.agenda_azul)
        binding.imageButton2.setImageResource(R.drawable.felicidade)
        binding.imageButton3.setImageResource(R.drawable.emergencia)

        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        binding.txtImageButton3.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))

        startActivity(Intent(this, ScheduleHomeScreen::class.java))
    }

    private fun selectProfileTab() {
        binding.imageButton4.setImageResource(R.drawable.perfil_colorido)
        binding.imageButton2.setImageResource(R.drawable.felicidade)
        binding.imageButton3.setImageResource(R.drawable.emergencia)
        binding.imageButton1.setImageResource(R.drawable.schedule)

        binding.txtImageButton4.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        binding.txtImageButton3.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
    }
}
