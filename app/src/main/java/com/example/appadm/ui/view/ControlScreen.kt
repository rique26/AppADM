package com.example.appadm.ui.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.appadm.R
import com.example.appadm.database.SymptomRoomDatabase
import com.example.appadm.database.models.Symptom
import com.example.appadm.databinding.ActivityControlScreenBinding
import com.example.appadm.databinding.ResCheckImageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ControlScreen : AppCompatActivity() {

    private lateinit var binding: ActivityControlScreenBinding
    private lateinit var db: SymptomRoomDatabase
    private var selectedDate = ""
    private var selectedTime = "14:32"
    private var currentSymptom = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ControlScreen", "onCreate")
        binding = ActivityControlScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWindowInsets()
        setupDatabase()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        Log.d("ControlScreen", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("ControlScreen", "onResume")
        setupImageButtonListeners()
    }



    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            SymptomRoomDatabase::class.java, "sintoma_database"
        ).build()
    }

    private fun setupClickListeners() {
        binding.buttonOpenCalendar.setOnClickListener { showCalendar() }
        binding.buttonOpenTimePicker.setOnClickListener { showClock() }
        binding.buttonSalvar.setOnClickListener { saveSymptom() }
    }

    private fun setupImageButtonListeners() {
        binding.imageButton11.setOnClickListener { setSymptom("Ótimo", R.drawable.rosto_1_40_azul, binding.imageButton11, binding.txtImageButton11) }
        binding.imageButton22.setOnClickListener { setSymptom("Bem", R.drawable.rosto_2_40_azul, binding.imageButton22, binding.txtImageButton22) }
        binding.imageButton33.setOnClickListener { setSymptom("Estranho", R.drawable.rosto_3_40_azul, binding.imageButton33, binding.txtImageButton33) }
        binding.imageButton44.setOnClickListener { setSymptom("Mal", R.drawable.rosto_4_40_azul, binding.imageButton44, binding.txtImageButton44) }
    }

    private fun setSymptom(text: String, imageResId: Int, imageButton: ImageButton, textButton: TextView) {
        resetImagesAndTexts()
        imageButton.setImageResource(imageResId)
        textButton.text = text
        currentSymptom = text
    }

    private fun resetImagesAndTexts() {
        binding.imageButton11.setImageResource(R.drawable.rosto_1_36)
        binding.imageButton22.setImageResource(R.drawable.rosto_2_36)
        binding.imageButton33.setImageResource(R.drawable.rosto_3_36)
        binding.imageButton44.setImageResource(R.drawable.rosto_4_36)
        binding.txtImageButton11.text = ""
        binding.txtImageButton22.text = ""
        binding.txtImageButton33.text = ""
        binding.txtImageButton44.text = ""
    }

    private fun showCalendar() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_calendar)
        val calendarView = dialog.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth / ${month + 1} / $year"
            binding.txtButtonData.text = selectedDate
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showClock() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_clock_dialog)
        val timePicker = dialog.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            selectedTime = "$hourOfDay:$minute"
            binding.txtButtonHora.text = selectedTime
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun saveSymptom() {
        val layout = ResCheckImageBinding.inflate(LayoutInflater.from(this))
        val toast = Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            view = layout.root
            setGravity(Gravity.CENTER, 0, binding.buttonSalvar.top)
        }
        toast.show()

        val newSymptom = Symptom("Sintoma", selectedTime, currentSymptom, selectedDate)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { db.sintomaDao().insert(newSymptom) }

            val replyIntent = Intent().apply {
                if (TextUtils.isEmpty(currentSymptom) && TextUtils.isEmpty(selectedTime) && TextUtils.isEmpty(selectedDate)) {
                    setResult(Activity.RESULT_CANCELED, this)
                } else {
                    putExtra("REPLY1", currentSymptom)
                    putExtra("REPLY2", selectedTime)
                    putExtra("REPLY3", selectedDate)
                    setResult(Activity.RESULT_OK, this)
                }
            }
            finish()
        }
    }
}
