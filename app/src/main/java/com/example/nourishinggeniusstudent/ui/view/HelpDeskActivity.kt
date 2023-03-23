package com.example.nourishinggeniusstudent.ui.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityHelpDeskBinding

class HelpDeskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpDeskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpDeskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backIconHelpdesk.setOnClickListener { finish() }
        Glide.with(this).load(getDrawable(R.drawable.question_mark_bg)).override(312, 312)
            .into(binding.questionIcon)
    }
}