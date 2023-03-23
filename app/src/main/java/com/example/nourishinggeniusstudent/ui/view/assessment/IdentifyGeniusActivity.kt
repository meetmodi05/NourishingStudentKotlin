package com.example.nourishinggeniusstudent.ui.view.assessment

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityIdentifyGeniusBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class IdentifyGeniusActivity : BaseActivity() {
    private lateinit var binding: ActivityIdentifyGeniusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyGeniusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }

    }
}