package com.example.appadm.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.appadm.R
import com.example.appadm.database.MedicineReminderDatabase
import com.example.appadm.database.daos.MedicineReminderDao
import com.example.appadm.database.models.MedicineReminder
import com.example.appadm.databinding.ActivityScheduleConfirmationMedicationScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleConfirmationMedicationScreen : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleConfirmationMedicationScreenBinding
    private var selectedTime1: String = ""
    private var selectedTime2: String = ""
    private var selectedTime3: String = ""
    private var selectedTime4: String = ""
    private lateinit var database: MedicineReminderDatabase
    private lateinit var medicineReminderDao: MedicineReminderDao
    private lateinit var selectedMedicamentoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityScheduleConfirmationMedicationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.database = MedicineReminderDatabase.getInstance(this)

        this.medicineReminderDao = this.database.medicamentoConfirmacaoDao()

        this.selectedMedicamentoName = intent.getStringExtra("SELECTED_MEDICAMENTO_NAME") ?: ""

    }

    override fun onStart() {
        super.onStart()

        binding.btQuantidade.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose)
        }

        binding.btHora.setOnClickListener {
            showTimePickerDialog { selectedTime ->
                selectedTime1 = selectedTime
                binding.horario.text =
                    selectedTime  // Atualiza o TextView com o horário selecionado
                println("Hora selecionada 1: $selectedTime")
            }
        }

        binding.btQuantidade2.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose2)
        }

        binding.btHora2.setOnClickListener {
            showTimePickerDialog { selectedTime ->
                selectedTime2 = selectedTime
                binding.horario2.text =
                    selectedTime  // Atualiza o TextView com o horário selecionado
                println("Hora selecionada 2: $selectedTime")
            }
        }

        binding.btQuantidade3.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose3)
        }

        binding.btHora3.setOnClickListener {
            showTimePickerDialog { selectedTime ->
                selectedTime3 = selectedTime
                binding.horario3.text =
                    selectedTime  // Atualiza o TextView com o horário selecionado
                println("Hora selecionada 3: $selectedTime")
            }
        }

        binding.btQuantidade4.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose4)
        }

        binding.btHora4.setOnClickListener {
            showTimePickerDialog { selectedTime ->
                selectedTime4 = selectedTime
                binding.horario4.text =
                    selectedTime  // Atualiza o TextView com o horário selecionado
                println("Hora selecionada 4: $selectedTime")
            }
        }


        binding.fab.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                val result = saveMedicamentoConfirmacao(selectedMedicamentoName)


                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        this@ScheduleConfirmationMedicationScreen,
                        if (result) "" else "Error trying to save MedicamentoConfirmacao",
                        Toast.LENGTH_LONG
                    ).show()

                    if (result)
                        finish()

                }

            }

            startActivity(Intent(this, ScheduleHomeScreen::class.java))
        }




        binding.checkBox1.setOnCheckedChangeListener { _, isChecked ->
            binding.cardView3.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.checkBox2.setOnCheckedChangeListener { _, isChecked ->
            binding.cardView4.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.checkBox3.setOnCheckedChangeListener { _, isChecked ->
            binding.cardView5.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

    }

    private fun showCustomPopup(anchorView: View, targetTextView: TextView) {
        // Inflate the PopupWindow layout
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.layout_custom_popup, null)

        // Create the PopupWindow
        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        // Calculate the position of the button on the screen
        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val buttonX = location[0] + anchorView.width
        val buttonY = location[1]

        val bt_cp = popupView.findViewById<Button>(R.id.bt_comprimido)
        val bt_g = popupView.findViewById<Button>(R.id.bt_grama)
        val bt_mg = popupView.findViewById<Button>(R.id.bt_miligrama)
        val bt_mcg = popupView.findViewById<Button>(R.id.bt_micrograma)
        val bt_ui = popupView.findViewById<Button>(R.id.bt_ui)
        var msg = ""

        val ed_qtd = popupView.findViewById<EditText>(R.id.ed_quantidade)

        val buttonClickListener = View.OnClickListener {
            msg = when (it.id) {
                R.id.bt_comprimido -> "cp"
                R.id.bt_grama -> "g"
                R.id.bt_miligrama -> "mg"
                R.id.bt_micrograma -> "mcg"
                R.id.bt_ui -> "ui"
                else -> ""
            }
            targetTextView.text = "${ed_qtd.text} $msg"
            popupWindow.dismiss()
        }

        bt_cp.setOnClickListener(buttonClickListener)
        bt_g.setOnClickListener(buttonClickListener)
        bt_mg.setOnClickListener(buttonClickListener)
        bt_mcg.setOnClickListener(buttonClickListener)
        bt_ui.setOnClickListener(buttonClickListener)

        // Adjust the position of the PopupWindow to align with the top of the button
        popupWindow.showAtLocation(anchorView, 0, buttonX, buttonY)
    }

    private fun showTimePickerDialog(onTimeSelected: (String) -> Unit) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_clock, null)
        val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                val hour = timePicker.hour
                val minute = timePicker.minute
                val selectedTime = String.format("%02d:%02d", hour, minute)
                onTimeSelected(selectedTime)  // Chama o callback com o horário selecionado
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                onTimeSelected("")
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }


    private suspend fun saveMedicamentoConfirmacao(name: String): Boolean {

        Log.d("TelaConfirmacaoMedicamentoAgenda", "Salvando Medicamento: $name")

        medicineReminderDao.insert(
            MedicineReminder(
                name,
                selectedTime1,
                selectedTime2,
                selectedTime3,
                selectedTime4
            )
        )

        return true
    }


    private fun deleteAllMedicamentos() {
        lifecycleScope.launch {
            medicineReminderDao.deleteAllMedicamentos()
            Toast.makeText(
                this@ScheduleConfirmationMedicationScreen,
                "Todos os medicamentos foram deletados.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}