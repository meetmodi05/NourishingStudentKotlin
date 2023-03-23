package com.example.nourishinggeniusstudent.ui.view.setting

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityFeedBackBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class FeedBack : BaseActivity() {
    private lateinit var binding:ActivityFeedBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backIconFeedback.setOnClickListener { finish() }
    }
}