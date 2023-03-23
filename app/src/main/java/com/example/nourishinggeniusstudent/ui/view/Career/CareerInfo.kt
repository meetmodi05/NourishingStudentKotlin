package com.example.nourishinggeniusstudent.ui.view.Career

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityCareerInfoBinding

class CareerInfo : AppCompatActivity() {
    private lateinit var binding: ActivityCareerInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

    }
}