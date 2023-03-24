package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivitySignupBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class SignupActivity : BaseActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel by lazy { AuthViewModel(this@SignupActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAero.setOnClickListener { finish() }
        binding.tvHaveAccount.setOnClickListener { finish() }
        binding.btnSignUp.setOnClickListener {
            if (validate()) {
                viewModel.isLoading.value = true
                viewModel.registerUser(
                    binding.etStudentEmail.text.toString().trim(),
                    binding.etStudentPassword.text.toString().trim()
                )
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.userData.observe(this) {
            viewModel.isLoading.value = false
            session?.user = it
            val intent = Intent(this@SignupActivity, DashBoardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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
        if (binding.etStudentPassword.text.toString() != binding.etStudentConfirmPassword.text.toString()) {
            binding.etStudentConfirmPassword.error = "Password is not match"
            return false
        }
        return true
    }

}