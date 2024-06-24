//package com.example.appadm.ViewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.appadm.database.models.SintomaControle
//import com.example.appadm.repositories.SintomaRepository
//import kotlinx.coroutines.launch
//
//
//class ControleInicialViewModel(private val repository: SintomaRepository) : ViewModel() {
//
//
//
//    val allSintomas: LiveData<List<SintomaControle>> = repository.allSintomas.asLiveData()
//
//    fun insert(sintoma: SintomaControle) = viewModelScope.launch {
//        repository.insert(sintoma)
//    }
//
//    private val _selectedDate = MutableLiveData<String>()
//    val selectedDate: LiveData<String>
//        get() = _selectedDate
//
//    private val _selectedTime = MutableLiveData<String>()
//    val selectedTime: LiveData<String>
//        get() = _selectedTime
//
//    // Add other properties as needed
//
//    fun onOpenCalendarClicked() {
//        // Logic to open calendar dialog
//        // For now, let's just set a sample date
//        _selectedDate.value = "10/05/2024"
//    }
//
//    fun onOpenTimePickerClicked() {
//        // Logic to open time picker dialog
//        // For now, let's just set a sample time
//        _selectedTime.value = "12:00"
//    }
//
//    // Add other methods to handle UI interactions and business logic
//}
//
//class ControleInicialViewModelFactory(private val repository: SintomaRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ControleInicialViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ControleInicialViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}