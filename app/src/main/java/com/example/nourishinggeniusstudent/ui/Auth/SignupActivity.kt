package com.example.nourishinggeniusstudent.ui.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAero.setOnClickListener { finish() }
        binding.tvHaveAccount.setOnClickListener { finish() }
        binding.btnSignUp.setOnClickListener {
            validate()
        }
    }

    private fun validate(): Boolean {
        if (binding.etStudentName.length() == 0) {
            binding.etStudentName.error = "Name is Required"
        }
        if (binding.etStudentEmail.length() == 0) {
            binding.etStudentEmail.error = "Email is Required"
            return false
        }
        if (binding.etStudentPassword.length() == 0) {
            binding.etStudentPassword.error = "Password is Required"
            return false
        }
        if (binding.etStudentContact.length() == 0) {
            binding.etStudentContact.error = "Contact is Required"
            return false
        }
        if (binding.etStudentConfirmPassword.length() == 0) {
            binding.etStudentConfirmPassword.error = "Confirm Password is Required"
            return false
        }
        if (binding.etStudentConfirmPassword != binding.etStudentPassword) {
            binding.etStudentConfirmPassword.error = "Password is not match"
            return false
        }
        return true
    }

}