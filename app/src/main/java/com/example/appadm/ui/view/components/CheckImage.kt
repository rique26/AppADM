package com.example.appadm.ui.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.appadm.databinding.ResCheckImageBinding


class CheckImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ResCheckImageBinding

    init {

        val inflater = LayoutInflater.from(context)
        binding = ResCheckImageBinding.inflate(inflater, this, true)
    }

}