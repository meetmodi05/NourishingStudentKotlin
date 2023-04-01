package com.example.nourishinggeniusstudent.model.response

import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.google.gson.annotations.SerializedName

data class CaseStudyResponseModel(
    @SerializedName("total_casestudys") val totalCaseStudy: Int? = null,
    @SerializedName("casestudys_posts") val list: List<CaseStudyData> = arrayListOf()
)
