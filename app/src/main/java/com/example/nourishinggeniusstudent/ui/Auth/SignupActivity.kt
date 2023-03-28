package com.example.nourishinggeniusstudent.ui.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nourishinggeniusstudent.Networking.ApiInterface
import com.example.nourishinggeniusstudent.Networking.Networking
import com.example.nourishinggeniusstudent.databinding.ActivitySignupBinding
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var registerInterface: ApiInterface
    val role: String = "student"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackAero.setOnClickListener { finish() }
        binding.tvHaveAccount.setOnClickListener { finish() }
        binding.btnSignUp.setOnClickListener {

            validate()

            GlobalScope.launch { register() }
        }
    }

    private fun toRequestBody(value: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
    }

    private fun register() {
        val r = Networking().getRetrofit()
        val map = mutableMapOf<String, RequestBody>()
        map.put("email_address", toRequestBody(binding.etStudentEmail.text.toString()))
        map.put("password", toRequestBody(binding.etStudentPassword.text.toString()))
        map.put("name", toRequestBody(binding.etStudentName.text.toString()))
        map.put("role", toRequestBody(role))
        map.put("contact_number", toRequestBody(binding.etStudentContact.text.toString()))

        val callback = r.createUser(map)
        callback.enqueue(object : Callback<SignUpModel> {
            override fun onResponse(
                call: Call<SignUpModel>, response: Response<SignUpModel>
            ) {
                Log.d("Success", response.message() + response.code())
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_LONG)
                        .show()
                }
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            }

            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })

//        GlobalScope.launch(Dispatchers.IO)
//        {
//
//            registerInterface.createUser(map).enqueue(object : Callback<List<SignUpModel>> {
//                override fun onResponse(
//                    call: Call<List<SignUpModel>>,
//                    response: Response<List<SignUpModel>>
//                ) {
//
//                }
//
//                override fun onFailure(call: Call<List<SignUpModel>>, t: Throwable) {
//
//                }
//            })
//        }
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