package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityForgotPasswordBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.isEmail
import com.example.nourishinggeniusstudent.utils.showToast

class ForgotPassword : BaseActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val viewModel by lazy { AuthViewModel(this) }
    private var state = FORGOT_PWD

    companion object {
        const val FORGOT_PWD = 0
        const val SUBMIT_OTP = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setClickListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.forgotPwd.observe(this) {
            state = SUBMIT_OTP
            initView()
        }
        viewModel.otpResponse.observe(this) {
            showToast("OTP verified")
//            finish()
            startActivity(Intent(this@ForgotPassword, ResetPassword::class.java))
        }
    }

    private fun setClickListeners() {
        binding.ivBackAero.setOnClickListener { finish() }
        binding.btnSubmit.setOnClickListener {
            if (state == FORGOT_PWD) {
                forgotPassword()
            } else {
                verifyOtp()
            }
        }
    }

    private fun verifyOtp() {
        if (binding.etOtp.text.toString().trim().length < 6) {
            binding.etOtp.error = "Invalid OTP"
            return
        }
        viewModel.isLoading.value = true
        viewModel.verifyOtp(binding.etEmail.text.toString(), binding.etOtp.text.toString())
    }

    private fun forgotPassword() {
        if (!binding.etEmail.text.toString().isEmail()) {
            binding.etEmail.error = "Invalid Email"
            return
        }
        viewModel.isLoading.value = true
        viewModel.forgotPwd(binding.etEmail.text.toString())
    }

    private fun initView() {
        when (state) {
            FORGOT_PWD -> {
                binding.etEmail.isEnabled = true
                binding.llOtp.isVisible = false
                binding.btnSubmit.text = getString(R.string.submit)
            }
            SUBMIT_OTP -> {
                binding.etEmail.isEnabled = false
                binding.llOtp.isVisible = true
                binding.btnSubmit.text = getString(R.string.verify)
            }
        }
        viewModel.isLoading.value = false
    }
}