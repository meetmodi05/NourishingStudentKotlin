package com.example.nourishinggeniusstudent.model.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("token") val token: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
) : Serializable
