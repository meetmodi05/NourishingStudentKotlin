package com.example.nourishinggeniusstudent.model.data

import com.google.gson.annotations.SerializedName

data class Packages(
    @SerializedName("package_id") var packageId: Int? = null,
    @SerializedName("package_title") var packageTitle: String? = null,
    @SerializedName("package_content") var packageContent: String? = null,
    @SerializedName("package_cost") var packageCost: String? = null,
    @SerializedName("button_url") var buttonUrl: String? = null,
    @SerializedName("button_target") var buttonTarget: String? = null,
    @SerializedName("button_text") var buttonText: String? = null
)
