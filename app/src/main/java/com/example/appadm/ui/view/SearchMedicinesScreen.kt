package com.example.appadm.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appadm.R
import com.example.appadm.ui.adapters.MedicineAdapter
import com.example.appadm.database.AllMedicinesRoomDatabase
import com.example.appadm.database.daos.MedicineDao
import com.example.appadm.database.models.AllMedicines
import com.example.appadm.databinding.ActivitySearchMedicinesScreenBinding
import com.example.appadm.datasource.FourPlusMedicines
import com.example.appadm.model.Medicamento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMedicinesScreen : AppCompatActivity(), MedicineAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: MedicineAdapter
    private var medicamentosList = listOf<Medicamento>()
    private var filteredMedicamentosList = listOf<Medicamento>()
    private lateinit var binding: ActivitySearchMedicinesScreenBinding
    private lateinit var db: AllMedicinesRoomDatabase
    private lateinit var medicineDao: MedicineDao
    private lateinit var selectedMedicamentoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivitySearchMedicinesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        loadInitialData()

        db = Room.databaseBuilder(
            applicationContext,
            AllMedicinesRoomDatabase::class.java, "medicamento_database"
        ).build()




    }
    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart() foi chamado")



    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume() foi chamado")



    }

    private fun setupView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerview)
        searchEditText = findViewById(R.id.campo_pesquisa)
        setupRecyclerView()
        setupSearchListener()
    }

    private fun loadInitialData() {
        medicamentosList = FourPlusMedicines.createDataSet().take(30)
        adapter.updateList(medicamentosList)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MedicineAdapter(medicamentosList, this)
        recyclerView.adapter = adapter


    }

    private fun setupSearchListener() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterMedicamentos(s.toString())
            }
        })
    }

    private fun filterMedicamentos(query: String) {
        filteredMedicamentosList = FourPlusMedicines.createDataSet().filter { medicamento ->
            medicamento.produto.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredMedicamentosList)
    }

    override fun onItemClick(medicamento: Medicamento) {
        selectedMedicamentoName = medicamento.produto
        Log.d("TAG", "Selected Medicamento: $selectedMedicamentoName")
        // You can now use the selectedMedicamentoName for further actions
        val novoMedicamento = AllMedicines(selectedMedicamentoName)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.medicamentoaDao().insert(novoMedicamento)
            }
        }

        val intent = Intent(this, ScheduleConfirmationMedicationScreen::class.java).apply {
            putExtra("SELECTED_MEDICAMENTO_NAME", novoMedicamento.nome_medicamento)
        }

        //startActivity(Intent(this, TelaConfirmacaoMedicamentoAgenda::class.java))
        startActivity(intent)

    }


}
