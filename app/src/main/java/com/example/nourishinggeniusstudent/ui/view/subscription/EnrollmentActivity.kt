package com.example.nourishinggeniusstudent.ui.view.subscription

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityEnrollmentBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class EnrollmentActivity : BaseActivity() {

    private lateinit var binding: ActivityEnrollmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnrollmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIconEnrollment.setOnClickListener { finish() }
    }
}