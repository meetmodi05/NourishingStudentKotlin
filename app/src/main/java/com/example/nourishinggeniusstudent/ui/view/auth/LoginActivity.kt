package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityLoginBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            var signUpIntent = Intent(this, SignupActivity::class.java)
            startActivity(signUpIntent)
        }
        binding.forgotPasswordTV.setOnClickListener {
            var forgotIntent = Intent(this, ForgotPassword::class.java)
            startActivity(forgotIntent)
        }
        binding.loginBtn.setOnClickListener {
            var dashboardIntent = Intent(this, DashBoardActivity::class.java)
            startActivity(dashboardIntent)
        }
    }
}