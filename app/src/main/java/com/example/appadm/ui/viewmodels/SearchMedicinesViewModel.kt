//package com.example.appadm.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import com.example.appadm.database.models.AllMedicines
//import com.example.appadm.model.Medicamento
//import com.example.appadm.repository.MedicineRepository
//import kotlinx.coroutines.launch
//
//class SearchMedicinesViewModel(private val repository: MedicineRepository) : ViewModel() {
//
//    private val _medicamentosList = MutableLiveData<List<Medicamento>>()
//    val medicamentosList: LiveData<List<Medicamento>> = _medicamentosList
//
//    private val _filteredMedicamentosList = MutableLiveData<List<Medicamento>>()
//    val filteredMedicamentosList: LiveData<List<Medicamento>> = _filteredMedicamentosList
//
//    private val _selectedMedicamentoName = MutableLiveData<String>()
//    val selectedMedicamentoName: LiveData<String> = _selectedMedicamentoName
//
//    init {
//        loadInitialData()
//    }
// /
//    fun loadInitialData() {
//        _medicamentosList.value = repository.getInitialMedicines()
//    }
//
//    fun filterMedicamentos(query: String) {
//        val filteredList = _medicamentosList.value.orEmpty().filter { medicamento ->
//            medicamento.produto.contains(query, ignoreCase = true)
//        }
//        _filteredMedicamentosList.value = filteredList
//    }
//
//    fun onItemClick(medicamento: Medicamento) {
//        _selectedMedicamentoName.value = medicamento.produto
//    }
//
//
//
//class SearchMedicinesViewModelFactory(private val repository: MedicineRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SearchMedicinesViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return SearchMedicinesViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
