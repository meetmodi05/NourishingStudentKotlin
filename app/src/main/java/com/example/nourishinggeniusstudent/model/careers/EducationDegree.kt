package com.example.nourishinggeniusstudent.model.careers

import com.google.gson.annotations.SerializedName

data class EducationDegree(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("logo") val logo: Int,
) : java.io.Serializable
