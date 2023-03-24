package com.example.nourishinggeniusstudent.model.careers

import com.google.gson.annotations.SerializedName

data class Careers(
    @SerializedName("career_id") val career_id: Int = 0,
    @SerializedName("career_title") val career_title: String = "",
    @SerializedName("career_logo") val career_logo: String = "",
) : java.io.Serializable
