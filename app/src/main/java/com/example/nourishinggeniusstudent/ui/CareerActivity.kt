package com.example.nourishinggeniusstudent.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nourishinggeniusstudent.databinding.ActivityCareerBinding

class CareerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

    }
}