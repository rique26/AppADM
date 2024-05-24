package com.example.appadm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.adapters.MedicamentoAdapter
import com.example.appadm.data.DataSource
import com.example.appadm.databinding.ActivityMedicamentos1Binding
import com.example.appadm.models.Medicamento

class Medicamentos1 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: MedicamentoAdapter
    private var medicamentosList = listOf<Medicamento>()
    private var filteredMedicamentosList = listOf<Medicamento>()
    private lateinit var binding: ActivityMedicamentos1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityMedicamentos1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        loadInitialData()

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
        medicamentosList = DataSource.createDataSet().take(30)
        adapter.updateList(medicamentosList)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MedicamentoAdapter(medicamentosList,)
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
        filteredMedicamentosList = DataSource.createDataSet().filter { medicamento ->
            medicamento.produto.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredMedicamentosList)
    }
}
