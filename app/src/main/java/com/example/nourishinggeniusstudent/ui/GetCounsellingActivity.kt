package com.example.nourishinggeniusstudent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityGetCounsellingBinding

class GetCounsellingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetCounsellingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCounsellingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }
    }
}