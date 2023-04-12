package com.example.nourishinggeniusstudent.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DashboardModel : Serializable {
    @SerializedName("status")
    var statusCode: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data_post")
    var dataPost: List<DataPost>? = null

    @SerializedName("data_dexperts")
    var dataDomainExport: List<DataDomainExport>? = null

    @SerializedName("data_careers")
    var dataCareers: List<DataCareers>? = null

}


class DataPost {

    @SerializedName("total_post")
    var totalPost: Int? = null

    @SerializedName("default_posts")
    var defaultPosts: List<DefaultPost>? = null
}

class DefaultPost {
    @SerializedName("post_id")
    var id: Int? = null

    @SerializedName("post_title")
    var title: String? = null

    @SerializedName("post_image_url")
    var img: Int? = null
}

class DataDomainExport {
    @SerializedName("total_domain_expert")
    var total: Int? = null

    @SerializedName("dexperts_posts")
    var post: List<ExportPost>? = null
}

class ExportPost {
    @SerializedName("dexperts_id")
    var dExpertId: Int? = null

    @SerializedName("dexperts_title")
    var title: String? = null

    @SerializedName("post_image_url")
    var img: Int? = null
}

class DataCareers {
    @SerializedName("total_casestody")
    var totalCaseStudy: Int? = null

    @SerializedName("casestudys_posts")
    var caseStudyPosts: List<CaseStudyPost>? = null
}

class CaseStudyPost {
    @SerializedName("CaseStudyPost")
    var id: Int? = null

    @SerializedName("casestodys_title")
    var cstitle: String? = null

    @SerializedName("post_image_url")
    var image: Int? = null
}
