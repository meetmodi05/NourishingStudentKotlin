package com.example.nourishinggeniusstudent.ui.view.profile

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityProfileBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crossIcon.setOnClickListener { finish() }
    }
}