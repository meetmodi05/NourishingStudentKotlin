package com.example.nourishinggeniusstudent.model.response

import com.example.nourishinggeniusstudent.model.careers.Careers
import com.google.gson.annotations.SerializedName

data class CareersListResponseModel(
    @SerializedName("total_careers")
    val totalCareers : Int = 0,
    @SerializedName("careers_posts")
    val careers_posts : ArrayList<Careers> = arrayListOf(),
):java.io.Serializable
