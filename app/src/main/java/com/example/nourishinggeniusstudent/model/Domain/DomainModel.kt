package com.example.nourishinggeniusstudent.model.Domain

import com.google.gson.annotations.SerializedName

class DomainModel(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("term_name") val termName: String? = null,
    @SerializedName("term_description") val description: String? = null,
    @SerializedName("term_slug") val descriptionSlug: String? = null,
)