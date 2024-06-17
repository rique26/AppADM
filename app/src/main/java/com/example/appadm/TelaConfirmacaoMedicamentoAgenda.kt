package com.example.appadm

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appadm.databinding.ActivityTelaConfirmacaoMedicamentoAgendaBinding

class TelaConfirmacaoMedicamentoAgenda : AppCompatActivity() {

    private lateinit var binding: ActivityTelaConfirmacaoMedicamentoAgendaBinding
    private lateinit var horaSelecionada: String
    private lateinit var qtdSelecionada: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTelaConfirmacaoMedicamentoAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        binding.btQuantidade.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose)
        }

        binding.btHora.setOnClickListener {
            showTimePickerDialog(binding.horario)
        }

        binding.btQuantidade2.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose2)
        }

        binding.btHora2.setOnClickListener {
            showTimePickerDialog(binding.horario2)
        }

        binding.btQuantidade3.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose3)
        }

        binding.btHora3.setOnClickListener {
            showTimePickerDialog(binding.horario3)
        }

        binding.btQuantidade4.setOnClickListener {
            showCustomPopup(it, binding.quantidadeDose4)
        }

        binding.btHora4.setOnClickListener {
            showTimePickerDialog(binding.horario4)
        }




        binding.fab.setOnClickListener {
            startActivity(Intent(this, TelaAgendaInicial::class.java))
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
        val popupWindow = PopupWindow(popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)

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

    private fun showTimePickerDialog(targetTextView: TextView) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_clock, null)
        val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val hour = timePicker.hour
                val minute = timePicker.minute
                val selectedTime = String.format("%02d:%02d", hour, minute)
                targetTextView.text = selectedTime
                println("Hora selecionada: $selectedTime")
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }


        val dialog = builder.create()
        dialog.show()
    }
    private fun buttonPopup(): String {
        val dialogView = layoutInflater.inflate(R.layout.layout_custom_popup, null)
        val bt_cp = dialogView.findViewById<Button>(R.id.bt_comprimido)
        val bt_g = dialogView.findViewById<Button>(R.id.bt_grama)
        val bt_mg = dialogView.findViewById<Button>(R.id.bt_miligrama)
        val bt_mcg = dialogView.findViewById<Button>(R.id.bt_micrograma)
        val bt_ui = dialogView.findViewById<Button>(R.id.bt_ui)
        var msg = ""

        bt_cp.setOnClickListener {
            msg = "cp"
        }
        bt_cp.setOnClickListener {
            msg = "g"
        }
        bt_cp.setOnClickListener {
            msg = "mg"
        }
        bt_cp.setOnClickListener {
            msg = "mcg"
        }
        bt_cp.setOnClickListener {
            msg = "ui"
        }

        return msg
    }

    override fun onResume() {
        super.onResume()



    }
}