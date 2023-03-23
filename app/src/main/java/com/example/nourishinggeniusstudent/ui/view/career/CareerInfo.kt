package com.example.nourishinggeniusstudent.ui.view.career

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityCareerInfoBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class CareerInfo : BaseActivity() {
    private lateinit var binding: ActivityCareerInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

    }
}