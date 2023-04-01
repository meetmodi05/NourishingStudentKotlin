package com.example.nourishinggeniusstudent.model.response

import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("verification") val verificationStatus: Boolean,
) : java.io.Serializable
