package com.example.appadm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.adapters.TelaControleInicialAdapter
import com.example.appadm.data.DataSourceSintomas
import com.example.appadm.databinding.ActivityTelaControleInicialBinding
import com.example.appadm.models.Sintoma

class TelaControleInicial : AppCompatActivity() {
    private lateinit var binding: ActivityTelaControleInicialBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TelaControleInicialAdapter
    private var sintomasList = listOf<Sintoma>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTelaControleInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TelaControleInicialAdapter(sintomasList)
        recyclerView.adapter = adapter
        adapter.updateList(sintomasList)




    }

    override fun onResume() {
        super.onResume()

        binding.imageButton2.setImageResource(R.drawable.controle_azul)
        binding.txtImageButton2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))

        updateRecyclerView(DataSourceSintomas.getSintomas())

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

            startActivity(Intent(this, TelaAgendaInicial::class.java))

        }
        binding.fab.setOnClickListener {
            startActivity(Intent(this, TelaControle::class.java))
        }
        if (DataSourceSintomas.getSintomas().isEmpty()) {
            binding.txtSintomas.visibility = View.VISIBLE
            binding.imgArrow.visibility = View.VISIBLE
        } else {
            binding.txtSintomas.visibility = View.GONE
            binding.imgArrow.visibility = View.GONE
        }


    }

    private fun updateRecyclerView(listaSintomas: ArrayList<Sintoma>) {
        adapter.updateList(listaSintomas)
    }
}