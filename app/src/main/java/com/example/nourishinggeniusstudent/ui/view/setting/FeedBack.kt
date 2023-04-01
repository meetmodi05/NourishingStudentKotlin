package com.example.nourishinggeniusstudent.ui.view.setting

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityFeedBackBinding
import com.example.nourishinggeniusstudent.ui.view.auth.AuthViewModel
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.showToast

class FeedBack : BaseActivity() {
    private lateinit var binding: ActivityFeedBackBinding
    private val viewModel by lazy { AuthViewModel(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.feedback.observe(this) {
            viewModel.isLoading.value = false
            showToast(it)
            binding.etFeedback.setText("")
            finish()
        }
    }

    private fun setClickListener() {
        binding.backIconFeedback.setOnClickListener { finish() }
        binding.submitBtn.setOnClickListener {
            if (!binding.etFeedback.text.isNullOrBlank()) {
                session?.user?.userId?.let { it1 ->
                    viewModel.isLoading.value = true
                    viewModel.addUserFeedback(
                        it1, binding.etFeedback.text.toString()
                    )
                }
            }
        }
    }
}