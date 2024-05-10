package com.example.appadm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class ControleViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate

    private val _selectedTime = MutableLiveData<String>()
    val selectedTime: LiveData<String>
        get() = _selectedTime

    // Add other properties as needed

    fun onOpenCalendarClicked() {
        // Logic to open calendar dialog
        // For now, let's just set a sample date
        _selectedDate.value = "10/05/2024"
    }

    fun onOpenTimePickerClicked() {
        // Logic to open time picker dialog
        // For now, let's just set a sample time
        _selectedTime.value = "12:00"
    }

    // Add other methods to handle UI interactions and business logic
}
