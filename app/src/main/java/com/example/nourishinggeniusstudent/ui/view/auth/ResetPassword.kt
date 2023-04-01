package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityForgotPasswordBinding
import com.example.nourishinggeniusstudent.databinding.ActivityResetPasswordBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.utils.isEmail
import com.example.nourishinggeniusstudent.utils.isPhone
import com.example.nourishinggeniusstudent.utils.isValidPassword
import com.example.nourishinggeniusstudent.utils.showToast

class ResetPassword : BaseActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel by lazy { AuthViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setClickListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.resetPwd.observe(this@ResetPassword) {
            showToast(it.message)
            val intent = Intent(this@ResetPassword, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun setClickListeners() {
        binding.ivBackAero.setOnClickListener { finish() }
        binding.btnSubmit.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (!binding.etPassword.text.toString().isValidPassword()) {
            binding.etPassword.error = "Password is Required"
            return
        }
        if (binding.etConfirmPassword.text.isNullOrBlank()) {
            binding.etConfirmPassword.error = "Confirm Password is Required"
            return
        }
        if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
            binding.etConfirmPassword.error = "Password is not match"
            return
        }

        viewModel.resetPassword(
            session?.user?.userId.toString(), binding.etPassword.text.toString()
        )
    }


    private fun initView() {
        viewModel.isLoading.value = false
    }
}