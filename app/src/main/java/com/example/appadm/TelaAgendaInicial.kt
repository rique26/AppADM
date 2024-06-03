package com.example.appadm

import android.content.Intent
import android.os.Bundle
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
import com.example.appadm.adapters.MedicamentoAdapter
import com.example.appadm.adapters.MedicamentoAgendaAdapter
import com.example.appadm.data.DataSourceSintomas
import com.example.appadm.database.MedicamentoRoomDatabase
import com.example.appadm.database.models.MedicamentoAgenda
import com.example.appadm.databinding.ActivityTelaAgendaInicialBinding
import com.example.appadm.models.Medicamento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TelaAgendaInicial : AppCompatActivity(){

    private lateinit var binding: ActivityTelaAgendaInicialBinding
    private lateinit var database: MedicamentoRoomDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MedicamentoAgendaAdapter

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


        database = Room.databaseBuilder(
            applicationContext,
            MedicamentoRoomDatabase::class.java,
            "medicamento_database"
        ).build()

        recyclerView = binding.recyclerview
        adapter = MedicamentoAgendaAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from the database
        fetchMedicamentosFromDatabase()


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
            startActivity(Intent(this, TelaPerfil::class.java))

        }

    }

    private fun fetchMedicamentosFromDatabase() {
        lifecycleScope.launch {
            val medicamentos = withContext(Dispatchers.IO) {
                database.medicamentoaDao().getMedicamento()
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