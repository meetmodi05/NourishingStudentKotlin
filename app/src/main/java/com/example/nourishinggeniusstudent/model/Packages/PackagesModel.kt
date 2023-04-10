package com.example.nourishinggeniusstudent.model.Packages

import com.google.gson.annotations.SerializedName

data class PackageDataModel(

    @SerializedName("total_packages") var totalPackages: Int,
    @SerializedName("packages_posts") var packagesPosts: List<PackagesModel>

)

data class PackagesModel(

    @SerializedName("package_id") var packageId: Int,
    @SerializedName("package_title") var packageTitle: String,
    @SerializedName("package_content") var packageContent: String,
    @SerializedName("package_cost") var packageCost: String,
    @SerializedName("button_url") var buttonUrl: String,
    @SerializedName("button_target") var buttonTarget: String,
    @SerializedName("button_text") var buttonText: String

)