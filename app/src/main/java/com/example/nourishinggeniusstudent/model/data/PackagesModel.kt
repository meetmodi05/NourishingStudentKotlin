package com.example.nourishinggeniusstudent.model.data

import com.google.gson.annotations.SerializedName

data class PackagesModel(
    @SerializedName("total_packages") var totalPackages: Int? = null,
    @SerializedName("packages_posts") var packagesPosts: ArrayList<Packages> = arrayListOf()
) : java.io.Serializable