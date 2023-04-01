package com.example.nourishinggeniusstudent.model.casestudy

import com.google.gson.annotations.SerializedName

data class SortRoles(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("term_name") var termName: String? = null,
    @SerializedName("term_description") var termDescription: String? = null,
    @SerializedName("term_slug") var termSlug: String? = null,
    @SerializedName("term_post_count") var termPostCount: Int? = null
) : java.io.Serializable