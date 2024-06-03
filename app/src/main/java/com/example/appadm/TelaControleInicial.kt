package com.example.appadm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appadm.adapters.TelaControleInicialAdapter
import com.example.appadm.data.DataSourceSintomas
import com.example.appadm.database.SintomaRoomDatabase
import com.example.appadm.databinding.ActivityTelaControleInicialBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TelaControleInicial : AppCompatActivity() {
    private lateinit var binding: ActivityTelaControleInicialBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TelaControleInicialAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var database: SintomaRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaControleInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TelaControleInicialAdapter(emptyList())
        recyclerView.adapter = adapter

        fab = binding.fab
        fab.setOnClickListener {
            startActivity(Intent(this, TelaControle::class.java))
        }

        // Initialize the database
        database = Room.databaseBuilder(
            applicationContext,
            SintomaRoomDatabase::class.java,
            "sintoma_database"
        ).build()

        // Fetch data from the database
        fetchSintomasFromDatabase()

        updateUiVisibility()
    }

    override fun onResume() {
        super.onResume()

        binding.imageButton2.setImageResource(R.drawable.controle_azul)
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))

        binding.imageButton2.setOnClickListener {
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

            startActivity(Intent(this, TelaAgendaInicial::class.java))
        }

        binding.imageButton4.setOnClickListener {
            binding.imageButton4.setImageResource(R.drawable.perfil_colorido)
            binding.imageButton2.setImageResource(R.drawable.felicidade)
            binding.imageButton3.setImageResource(R.drawable.emergencia)
            binding.imageButton1.setImageResource(R.drawable.schedule)

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

            startActivity(Intent(this, TelaPerfil::class.java))
        }

        fetchSintomasFromDatabase()
    }

    private fun fetchSintomasFromDatabase() {
        lifecycleScope.launch {
            val sintomas = withContext(Dispatchers.IO) {
                database.sintomaDao().getSintoma()
            }
            adapter.updateList(sintomas)

            if (sintomas.isNotEmpty()) {
                binding.txtSintomas.visibility = View.GONE
                binding.imgArrow.visibility = View.GONE
            } else {
                binding.txtSintomas.visibility = View.VISIBLE
                binding.imgArrow.visibility = View.VISIBLE
            }
        }
    }

    private fun updateUiVisibility() {
        if (DataSourceSintomas.getSintomas().isEmpty()) {
            binding.txtSintomas.visibility = View.VISIBLE
            binding.imgArrow.visibility = View.VISIBLE
        } else {
            binding.txtSintomas.visibility = View.GONE
            binding.imgArrow.visibility = View.GONE
        }
    }
}
