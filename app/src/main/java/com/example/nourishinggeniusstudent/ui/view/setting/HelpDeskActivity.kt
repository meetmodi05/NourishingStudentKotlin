package com.example.nourishinggeniusstudent.ui.view.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityHelpDeskBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.getImageProgress


class HelpDeskActivity : BaseActivity() {

    private lateinit var binding: ActivityHelpDeskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpDeskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backIconHelpdesk.setOnClickListener { finish() }
        Glide.with(this).load(getDrawable(R.drawable.question_mark_bg)).override(312, 312)
            .placeholder(getImageProgress()).into(binding.questionIcon)

        binding.contactBtn.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=+919510910352"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        
    }
}