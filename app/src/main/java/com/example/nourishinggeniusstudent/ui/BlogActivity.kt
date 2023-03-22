package com.example.nourishinggeniusstudent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityBlogBinding

class BlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }
    }
}