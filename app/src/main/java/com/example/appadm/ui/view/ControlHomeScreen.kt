package com.example.appadm.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appadm.R
import com.example.appadm.ui.adapters.ControlHomeScreenAdapter
import com.example.appadm.datasource.Symptom
import com.example.appadm.database.SymptomRoomDatabase
import com.example.appadm.databinding.ActivityControlHomeScreenBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ControlHomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityControlHomeScreenBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ControlHomeScreenAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var database: SymptomRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUIState()
        setupViews()
        fetchSintomasFromDatabase()
        updateUiVisibility()

    }

    override fun onResume() {
        super.onResume()
        setupButtonListeners()
        fetchSintomasFromDatabase()


    }

    private fun setupViews() {
        setupWindowInsets()
        setupRecyclerView()
        setupFab()
        setupDatabase()
    }





    private fun fetchSintomasFromDatabase() {
        lifecycleScope.launch {
            val sintomas = withContext(Dispatchers.IO) {
                database.sintomaDao().getSintoma()
            }
            adapter.updateList(sintomas)

            val isEmpty = sintomas.isEmpty() && Symptom.getSintomas().isEmpty()
            binding.txtSintomas.visibility = if (isEmpty) View.VISIBLE else View.GONE
            binding.imgArrow.visibility = if (isEmpty) View.VISIBLE else View.GONE
        }
    }

    private fun updateUiVisibility() {
        if (Symptom.getSintomas().isEmpty()) {
            binding.txtSintomas.visibility = View.VISIBLE
            binding.imgArrow.visibility = View.VISIBLE
        } else {
            binding.txtSintomas.visibility = View.GONE
            binding.imgArrow.visibility = View.GONE
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ControlHomeScreenAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun setupFab() {
        fab = binding.fab
        fab.setOnClickListener {
            startActivity(Intent(this, ControlScreen::class.java))
        }
    }

    private fun setupDatabase() {
        database = Room.databaseBuilder(
            applicationContext,
            SymptomRoomDatabase::class.java,
            "sintoma_database"
        ).build()
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
        binding.imageButton2.setImageResource(R.drawable.controle_azul)
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
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

        startActivity(Intent(this, ProfileHomeScreen::class.java))
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


}
