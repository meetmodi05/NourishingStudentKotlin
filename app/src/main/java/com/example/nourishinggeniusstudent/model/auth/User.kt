package com.example.nourishinggeniusstudent.model.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("token") val token: String,
    @SerializedName("id", alternate = ["user_id"]) val userId: String,
    @SerializedName("user_email", alternate = ["email_address"]) val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("name") val name: String,
    @SerializedName("contact_number") val contactNumber: String,
    @SerializedName("date_of_birth") val dob: String,
    @SerializedName("user_address") val address: String,
    @SerializedName("profile_pic") val profilePic: String,
) : Serializable
