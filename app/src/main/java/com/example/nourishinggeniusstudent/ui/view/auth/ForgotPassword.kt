package com.example.nourishinggeniusstudent.ui.view.auth

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityForgotPasswordBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class ForgotPassword : BaseActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAero.setOnClickListener { finish() }
    }
}