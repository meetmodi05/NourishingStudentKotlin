package com.example.nourishinggeniusstudent.model.domain

import com.google.gson.annotations.SerializedName

data class DomainData(
    @SerializedName("domain_expert_study_id", alternate = ["dexperts_id","domainexpert_id"]) var id: Int? = null,
    @SerializedName(
        "domain_expert_study_title", alternate = ["dexperts_title","domainexpert_title"]
    ) var title: String? = null,
    @SerializedName("domain_expert_study_content") var content: String? = null,
    @SerializedName("domain_expert_study_feature_uri", alternate = ["casestudy_feature_uri"]) var featureUri: String? = null,
    @SerializedName("domain_expert_position") var position: String? = null
) : java.io.Serializable
