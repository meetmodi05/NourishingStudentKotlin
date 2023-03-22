package com.example.nourishinggeniusstudent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAero.setOnClickListener { finish() }
        binding.tvHaveAccount.setOnClickListener { finish() }
    }
}