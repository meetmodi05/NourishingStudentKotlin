package com.example.nourishinggeniusstudent.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityFeedBackBinding

class FeedBack : AppCompatActivity() {
    private lateinit var binding:ActivityFeedBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backIconFeedback.setOnClickListener { finish() }
    }
}