package com.example.nourishinggeniusstudent.Networking.repo


import com.example.nourishinggeniusstudent.Networking.network.Networking
import com.example.nourishinggeniusstudent.databinding.ActivitySignupBinding
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.model.BaseModel
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class SignUpRepo {
    private lateinit var binding: ActivitySignupBinding
    private val role: String = "student"

    fun signIn(): Observable<BaseModel<SignUpModel>> {
        fun toRequestBody(value: String): RequestBody {
            return value.toRequestBody("text/plain".toMediaTypeOrNull())
        }

        val map = mutableMapOf<String, RequestBody>()
        map["email_address"] = toRequestBody(binding.etStudentEmail.text.toString())
        map["password"] = toRequestBody(binding.etStudentPassword.text.toString())
        map["name"] = toRequestBody(binding.etStudentName.text.toString())
        map["role"] = toRequestBody(role)
        map["contact_number"] = toRequestBody(binding.etStudentContact.text.toString())

        return Networking.with().getRetrofit().createUser(map)
    }
}