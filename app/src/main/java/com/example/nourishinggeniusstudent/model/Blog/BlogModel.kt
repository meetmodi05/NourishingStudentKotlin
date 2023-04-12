package com.example.nourishinggeniusstudent.model.Blog

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BlogDataModel {
    var data: List<BlogModel> = arrayListOf()
}


data class BlogModel(
    @SerializedName("post_image_url") var img: Boolean? = null,
    @SerializedName("post_title") var title: String? = null,
    @SerializedName("post_name") var name: String? = null
)

class BlogBaseModel<T> : Serializable {
    @SerializedName("status")
    var statusCode: Int = 0

    @SerializedName("message")
    var message: String = ""

    @SerializedName("data")
    var dataModel: List<BlogModel>? = null
}