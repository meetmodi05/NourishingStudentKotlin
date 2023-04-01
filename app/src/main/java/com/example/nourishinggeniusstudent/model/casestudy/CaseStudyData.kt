package com.example.nourishinggeniusstudent.model.casestudy

import com.google.gson.annotations.SerializedName

data class CaseStudyData(
    @SerializedName("casestudy_id", alternate = ["casestodys_id"]) var id: Int? = null,
    @SerializedName("casestudy_title", alternate = ["casestodys_title"]) var title: String? = null,
    @SerializedName("casestudy_content") var content: String? = null,
    @SerializedName("casestudy_feature_uri") var featureUri: String? = null
) : java.io.Serializable
