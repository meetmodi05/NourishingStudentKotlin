package com.example.nourishinggeniusstudent.model

import com.google.gson.annotations.SerializedName

class CaseStudiesModel(
    @SerializedName("term_name") val name: String? = null,
    @SerializedName("term_description") val termDescription: String? = null,
    @SerializedName("term_slug") val termSlug: String? = null,
    @SerializedName("casestudy_feature_uri") val img: Int? = null
)