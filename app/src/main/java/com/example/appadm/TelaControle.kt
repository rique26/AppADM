package com.example.appadm

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appadm.ViewModel.ControleViewModel
import com.example.appadm.data.DataSourceSintomas
import com.example.appadm.databinding.ActivityTelaControleBinding
import com.example.appadm.databinding.ResImgVerificadoBinding

class TelaControle : AppCompatActivity() {

    private lateinit var binding: ActivityTelaControleBinding
    private lateinit var txtButtonData: TextView
    private lateinit var buttonSalvar: Button
    private var dataSelecionada: String = ""
    private var horaSelecionada: String = "14:32"
    private var sintomaAtual: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTelaControleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("LOG", "onCreate() TelaControle")
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtButtonData = findViewById(R.id.txt_buttonData)
        buttonSalvar = findViewById(R.id.buttonSalvar)


    }

    override fun onStart() {
        super.onStart()
        Log.i("LOG", "onStart() TelaControle")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LOG", "onResume() TelaControle")

        val buttonOpenCalendar = findViewById<Button>(R.id.buttonOpenCalendar)
        buttonOpenCalendar.setOnClickListener {
            exibirCalendario()
        }

        val buttonOpenTimePicker = findViewById<Button>(R.id.buttonOpenTimePicker)
        buttonOpenTimePicker.setOnClickListener {
            exibirRelogio()
        }







        this.binding.imageButton11.setOnClickListener {
            // Altera a imagem do ImageButton
            setImages()
            setText()
            this.binding.imageButton11.setImageResource(R.drawable.rosto_1_40_azul)
            this.binding.txtImageButton11.text = "Ótimo"
            sintomaAtual = "Ótimo"

        }

        this.binding.imageButton22.setOnClickListener {
            // Altera a imagem do ImageButton
            setImages()
            setText()
            this.binding.imageButton22.setImageResource(R.drawable.rosto_2_40_azul)
            this.binding.txtImageButton22.text = "Bem"
            sintomaAtual = "Bem"

        }

        this.binding.imageButton33.setOnClickListener {
            // Altera a imagem do ImageButton
            setImages()
            setText()
            this.binding.imageButton33.setImageResource(R.drawable.rosto_3_40_azul)
            this.binding.txtImageButton33.text = "Estranho"
            sintomaAtual = "Estranho"

        }

        this.binding.imageButton44.setOnClickListener {
            // Altera a imagem do ImageButton
            setImages()
            setText()
            this.binding.imageButton44.setImageResource(R.drawable.rosto_4_40_azul)
            this.binding.txtImageButton44.text = "Mal"
            sintomaAtual = "Mal"

        }










        binding.buttonSalvar.setOnClickListener {
            DataSourceSintomas.createDataSet(dataSelecionada, horaSelecionada, sintomaAtual)
            val inflater = LayoutInflater.from(this)
            val layout = ResImgVerificadoBinding.inflate(inflater)

            val toast = Toast(this)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout.root

            // Definir o tamanho do Toast como MATCH_PARENT
            layout.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            // Definir a posição do Toast sobre o botão "Salvar"
            toast.setGravity(Gravity.CENTER or Gravity.CENTER,
                0,
                binding.buttonSalvar.top)

            // Exibir o Toast
            toast.show()

            startActivity(Intent(this, TelaControleInicial::class.java))

        }




}



    override fun onPause() {
        super.onPause()
        Log.i("LOG", "onPause() TelaControle")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LOG", "onStop() TelaControle")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LOG", "onDestroy() TelaControle")
    }


    private fun exibirCalendario() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_calendario)

        val calendarView = dialog.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dataSelecionada = "$dayOfMonth / ${month + 1} / $year"

            val txtData = findViewById<TextView>(R.id.txt_buttonData)
            txtData.text = dataSelecionada
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun exibirRelogio() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_dialog_relogio)

        val timePicker = dialog.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            horaSelecionada = "$hourOfDay:$minute"

            val txtHora = findViewById<TextView>(R.id.txt_buttonHora)
            txtHora.text = horaSelecionada
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setImages(){
        this.binding.imageButton33.setImageResource(R.drawable.rosto_3_36)
        this.binding.imageButton11.setImageResource(R.drawable.rosto_1_36)
        this.binding.imageButton22.setImageResource(R.drawable.rosto_2_36)
        this.binding.imageButton44.setImageResource(R.drawable.rosto_4_36)
    }

    private fun setText(){
        this.binding.txtImageButton44.text = ""
        this.binding.txtImageButton22.text = ""
        this.binding.txtImageButton33.text = ""
        this.binding.txtImageButton11.text = ""

    }
}
