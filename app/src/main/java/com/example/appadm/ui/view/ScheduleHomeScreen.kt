package com.example.appadm.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appadm.R
import com.example.appadm.ui.adapters.MedicamentoAgendaAdapter
import com.example.appadm.database.MedicineReminderDatabase
import com.example.appadm.database.daos.MedicineReminderDao
import com.example.appadm.database.models.MedicineReminder
import com.example.appadm.databinding.ActivityScheduleHomeScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleHomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleHomeScreenBinding
    private lateinit var database: MedicineReminderDatabase
    private lateinit var medicineReminderDao: MedicineReminderDao
    private lateinit var recyclerView: RecyclerView
    private var medicamentosList = listOf<MedicineReminder>()
    private lateinit var adapter: MedicamentoAgendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityScheduleHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        database = Room.databaseBuilder(
            applicationContext,
            MedicineReminderDatabase::class.java,
            "nome-do-banco-de-dados"
        ).build()




        recyclerView = binding.recyclerview
        adapter = MedicamentoAgendaAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        Log.d("TAG", "Depois do recyclerview")
        // Fetch data from the database
        fetchMedicamentosFromDatabase()

        Log.d("TAG", "Depois do fetchMedicamentos")


    }


    override fun onResume() {
        super.onResume()

        binding.imageButton1.setImageResource(R.drawable.agenda_azul)
        binding.txtImageButton1.setTextColor(ContextCompat.getColor(this, R.color.blue_light))

        binding.fab.setOnClickListener {
            startActivity(Intent(this, SearchMedicinesScreen::class.java))

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

            startActivity(Intent(this, ControlHomeScreen::class.java))

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

        binding.imageButton4.setOnClickListener {
            // Altera a imagem do ImageButton
            binding.imageButton4.setImageResource(R.drawable.perfil_colorido)
            binding.imageButton2.setImageResource(R.drawable.felicidade)
            binding.imageButton3.setImageResource(R.drawable.emergencia)
            binding.imageButton3.setImageResource(R.drawable.schedule)

            binding.txtImageButton4.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
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
            binding.txtImageButton1.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            startActivity(Intent(this, ProfileHomeScreen::class.java))

        }


    }

    private fun fetchMedicamentosFromDatabase() {
        lifecycleScope.launch {
            val medicamentos = withContext(Dispatchers.IO) {
                database.medicamentoConfirmacaoDao().getMedicamento()
            }
            adapter.updateList(medicamentos)

            if (medicamentos.isNotEmpty()) {
                binding.txtSintomas.visibility = View.GONE
            } else {
                binding.txtSintomas.visibility = View.VISIBLE
            }
        }

    }







}