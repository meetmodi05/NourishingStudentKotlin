package com.example.nourishinggeniusstudent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityIdentifyGeniusBinding

class IdentifyGeniusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentifyGeniusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyGeniusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }

    }
}