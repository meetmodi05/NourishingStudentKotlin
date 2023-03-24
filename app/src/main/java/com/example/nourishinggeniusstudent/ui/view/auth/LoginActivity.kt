package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityLoginBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy { AuthViewModel(this@LoginActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
        setObservers()


    }

    private fun setOnClickListeners() {
        binding.signUpBtn.setOnClickListener {
            var signUpIntent = Intent(this, SignupActivity::class.java)
            startActivity(signUpIntent)
        }
        binding.forgotPasswordTV.setOnClickListener {
            var forgotIntent = Intent(this, ForgotPassword::class.java)
            startActivity(forgotIntent)
        }
        binding.loginBtn.setOnClickListener {
            validate()
        }
    }

    private fun setObservers() {
        viewModel.userData.observe(this) {
            session?.user = it
            viewModel.isLoading.value = false
            var dashboardIntent = Intent(this, DashBoardActivity::class.java)
            startActivity(dashboardIntent)
        }
    }

    private fun validate() {
        if (binding.etEmail.text.isNullOrBlank()) {
            return
        }
        if (binding.etPassword.text.isNullOrBlank()) {
            return
        }
        viewModel.isLoading.value = true
        viewModel.loginUser(
            binding.etEmail.text.toString().trim(), binding.etPassword.text.toString()
        )
    }
}