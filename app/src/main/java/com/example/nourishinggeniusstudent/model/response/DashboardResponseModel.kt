package com.example.nourishinggeniusstudent.model.response

import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.google.gson.annotations.SerializedName

data class DashboardResponseModel(
    @SerializedName("message") var message: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("data_post") var dataPost: DataPost? = DataPost(),
    @SerializedName("data_dexperts") var dataDexperts: DataExperts? = DataExperts(),
    @SerializedName("data_careers") var dataCareers: DataCaseSudy? = DataCaseSudy()
) : java.io.Serializable

data class DataCaseSudy(
    @SerializedName("total_casestody") var totalCasestody: Int? = null,
    @SerializedName("casestudys_posts") var casestudysPosts: ArrayList<CaseStudyData> = arrayListOf()
)

data class DataExperts(
    @SerializedName("total_domain_expert") var totalDomainExpert: Int? = null,
    @SerializedName("dexperts_posts") var dexpertsPosts: ArrayList<DomainData> = arrayListOf()
)

data class DataPost(
    @SerializedName("total_post") var totalPost: Int? = null,
    @SerializedName("default_posts") var defaultPosts: ArrayList<BlogDataModel> = arrayListOf()
)
