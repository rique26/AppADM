package com.example.appadm

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.appadm.databinding.ResProfileImageBinding

class ProfileImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var visualized : Boolean = false

    private val binding: ResProfileImageBinding = ResProfileImageBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {

        context.obtainStyledAttributes(attrs, R.styleable.ProfileImage).apply {
            setImageProfile(getResourceId(R.styleable.ProfileImage_src, R.drawable.default_profile))

        }

    }



    fun setImageProfile(resourceId: Int) {
        this.binding.imgProfileImage.setImageResource(resourceId)
    }

}