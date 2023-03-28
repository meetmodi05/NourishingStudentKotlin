package com.example.nourishinggeniusstudent.model.Auth

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("email_address")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("contact_number")
    val contactNumber: String?
)