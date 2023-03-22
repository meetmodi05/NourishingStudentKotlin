package com.example.nourishinggeniusstudent.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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