package com.example.nourishinggeniusstudent.model.Career

import com.google.gson.annotations.SerializedName


class CareerDataModel {
    @SerializedName("total_careers")
    val totalCareers: String? = null

    @SerializedName("careers_posts")
    val careersPosts: List<CareerPost> = arrayListOf()
}

data class CareerPost(
    @SerializedName("career_id") val id: Int? = null,

    @SerializedName("career_title") val careerTitle: String? = null,

    @SerializedName("career_logo") val img: String? = null
)