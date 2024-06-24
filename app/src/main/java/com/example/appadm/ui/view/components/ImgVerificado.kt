package com.example.appadm.ui.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.appadm.databinding.ResCheckImageBinding


class ImgVerificado @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ResCheckImageBinding // Substitua "SeuLayout" pelo nome do seu layout XML

    init {
        // Inflar o layout XML na custom view
        val inflater = LayoutInflater.from(context)
        binding = ResCheckImageBinding.inflate(inflater, this, true) // Substitua "SeuLayout" pelo nome do seu layout XML
    }

    // Você pode adicionar métodos adicionais para configurar a custom view conforme necessário
}