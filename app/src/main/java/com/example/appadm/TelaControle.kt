package com.example.appadm

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appadm.ViewModel.ControleViewModel

class TelaControle : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_controle)
        Log.i("LOG", "onCreate() TelaControle")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


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

        val imageButton1 = findViewById<ImageButton>(R.id.imageButton1_1)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2_2)
        val imageButton3 = findViewById<ImageButton>(R.id.imageButton3_3)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4_4)


        val txtDisposicao1 = findViewById<TextView>(R.id.txt_imageButton1_1)
        val txtDisposicao2 = findViewById<TextView>(R.id.txt_imageButton2_2)
        val txtDisposicao3 = findViewById<TextView>(R.id.txt_imageButton3_3)
        val txtDisposicao4 = findViewById<TextView>(R.id.txt_imageButton4_4)


        imageButton1.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButton1.setImageResource(R.drawable.rosto_1_40_azul)
            imageButton2.setImageResource(R.drawable.rosto_2_36)
            imageButton3.setImageResource(R.drawable.rosto_3_36)
            imageButton4.setImageResource(R.drawable.rosto_4_36)

            txtDisposicao1.text = "Ótimo"
            txtDisposicao2.text = ""
            txtDisposicao3.text = ""
            txtDisposicao4.text = ""
        }

        imageButton2.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButton2.setImageResource(R.drawable.rosto_2_40_azul)
            imageButton1.setImageResource(R.drawable.rosto_1_36)
            imageButton3.setImageResource(R.drawable.rosto_3_36)
            imageButton4.setImageResource(R.drawable.rosto_4_36)

            txtDisposicao2.text = "Bem"
            txtDisposicao1.text = ""
            txtDisposicao3.text = ""
            txtDisposicao4.text = ""
        }

        imageButton3.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButton3.setImageResource(R.drawable.rosto_3_40_azul)
            imageButton1.setImageResource(R.drawable.rosto_1_36)
            imageButton2.setImageResource(R.drawable.rosto_2_36)
            imageButton4.setImageResource(R.drawable.rosto_4_36)

            txtDisposicao3.text = "Estranho"
            txtDisposicao2.text = ""
            txtDisposicao1.text = ""
            txtDisposicao4.text = ""
        }

        imageButton4.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButton4.setImageResource(R.drawable.rosto_4_40_azul)
            imageButton1.setImageResource(R.drawable.rosto_1_36)
            imageButton2.setImageResource(R.drawable.rosto_2_36)
            imageButton3.setImageResource(R.drawable.rosto_3_36)

            txtDisposicao4.text = "Mal"
            txtDisposicao2.text = ""
            txtDisposicao3.text = ""
            txtDisposicao1.text = ""
        }

        val includeView = findViewById<ConstraintLayout>(R.id.include)
        val imageButtonInInclude2 = includeView.findViewById<ImageButton>(R.id.imageButton2)
        val imageButtonInInclude3 = includeView.findViewById<ImageButton>(R.id.imageButton3)
        val imageButtonInInclude1 = includeView.findViewById<ImageButton>(R.id.imageButton1)

        val textViewToChangeColor2 = findViewById<TextView>(R.id.txt_imageButton2)
        val textViewToChangeColor3 = findViewById<TextView>(R.id.txt_imageButton3)
        val textViewToChangeColor1 = findViewById<TextView>(R.id.txt_imageButton1)

        imageButtonInInclude2.setImageResource(R.drawable.controle_azul)

        imageButtonInInclude2.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButtonInInclude2.setImageResource(R.drawable.controle_azul)
            imageButtonInInclude1.setImageResource(R.drawable.schedule)
            imageButtonInInclude3.setImageResource(R.drawable.emergencia)

            textViewToChangeColor2.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            textViewToChangeColor1.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            textViewToChangeColor3.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )

        }

        imageButtonInInclude3.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButtonInInclude3.setImageResource(R.drawable.controle_azul)
            imageButtonInInclude1.setImageResource(R.drawable.schedule)
            imageButtonInInclude2.setImageResource(R.drawable.felicidade)

            textViewToChangeColor3.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            textViewToChangeColor1.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            textViewToChangeColor2.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
        }

        imageButtonInInclude1.setOnClickListener {
            // Altera a imagem do ImageButton
            imageButtonInInclude1.setImageResource(R.drawable.controle_azul)
            imageButtonInInclude2.setImageResource(R.drawable.felicidade)
            imageButtonInInclude3.setImageResource(R.drawable.emergencia)

            textViewToChangeColor1.setTextColor(ContextCompat.getColor(this, R.color.blue_light))
            textViewToChangeColor2.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
            textViewToChangeColor3.setTextColor(
                ContextCompat.getColor(
                    this,
                    androidx.cardview.R.color.cardview_dark_background
                )
            )
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
            val dataSelecionada = "$dayOfMonth / ${month + 1} / $year"

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
            val horaSelecionada = "$hourOfDay:$minute"

            val txtHora = findViewById<TextView>(R.id.txt_buttonHora)
            txtHora.text = horaSelecionada

            dialog.dismiss()
        }

        dialog.show()
    }

}
