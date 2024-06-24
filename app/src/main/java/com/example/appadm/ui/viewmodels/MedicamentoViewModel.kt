package com.example.appadm.ui.viewmodels

//class MedicamentoViewModel(private val repository: MedicamentoRepository) : ViewModel() {
//
//    val allMedicamentos: LiveData<List<Medicamento>> = repository.allMedicamentos.asLiveData()
//
//    fun insert(word: Medicamento) = viewModelScope.launch {
//        repository.insert(word)
//    }
//}
//
//class MedicamentoViewModelFactory(private val repository: MedicamentoRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MedicamentoViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return MedicamentoViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}