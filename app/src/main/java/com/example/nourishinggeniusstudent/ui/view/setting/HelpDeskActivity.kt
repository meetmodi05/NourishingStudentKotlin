package com.example.nourishinggeniusstudent.ui.view.setting

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityHelpDeskBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class HelpDeskActivity : BaseActivity() {

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