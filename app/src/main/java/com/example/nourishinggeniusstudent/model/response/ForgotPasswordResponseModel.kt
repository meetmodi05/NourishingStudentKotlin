package com.example.nourishinggeniusstudent.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ForgotPasswordResponseModel(
    @SerializedName("otp") val otp: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("email_address") val email: String,
) : Serializable
