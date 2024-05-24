package com.example.appadm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appadm.data.DataSourceSintomas
import com.example.appadm.databinding.ActivityTelaAgendaInicialBinding
import com.example.appadm.databinding.ActivityTelaControleInicialBinding

class TelaAgendaInicial : AppCompatActivity() {

    private lateinit var binding: ActivityTelaAgendaInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTelaAgendaInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onResume() {
        super.onResume()

        binding.imageButton1.setImageResource(R.drawable.agenda_azul)
        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, R.color.blue_light))

        binding.fab.setOnClickListener {
            startActivity(Intent(this, Medicamentos1::class.java))
        }

        binding.imageButton2.setOnClickListener {
            // Altera a imagem do ImageButton
            binding.imageButton2.setImageResource(R.drawable.controle_azul)
            binding.imageButton1.setImageResource(R.drawable.schedule)
            binding.imageButton3.setImageResource(R.drawable.emergencia)

            binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            binding.txtImageButton1.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            binding.txtImageButton3.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )

            startActivity(Intent(this, TelaControleInicial::class.java))

        }

        binding.imageButton3.setOnClickListener {
            // Altera a imagem do ImageButton
            binding.imageButton3.setImageResource(R.drawable.contato_azul)
            binding.imageButton1.setImageResource(R.drawable.schedule)
            binding.imageButton2.setImageResource(R.drawable.felicidade)

            binding.txtImageButton3.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            binding.txtImageButton1.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            binding.txtImageButton2.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
        }

        binding.imageButton1.setOnClickListener {
            // Altera a imagem do ImageButton
            binding.imageButton1.setImageResource(R.drawable.agenda_azul)
            binding.imageButton2.setImageResource(R.drawable.felicidade)
            binding.imageButton3.setImageResource(R.drawable.emergencia)

            binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            binding.txtImageButton2.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            binding.txtImageButton3.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )

        }
    }


}