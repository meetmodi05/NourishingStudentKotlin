package com.example.nourishinggeniusstudent.model

import com.google.gson.annotations.SerializedName

class TopExpertModel(
    @SerializedName("domain_expert_study_feature_uri") val img: Int? = null,
    @SerializedName("domain_expert_study_title") val title: String? = null,
)
