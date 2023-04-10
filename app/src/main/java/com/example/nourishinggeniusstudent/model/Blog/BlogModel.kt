package com.example.nourishinggeniusstudent.model.Blog

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BlogDataModel {
    var data: List<BlogModel> = arrayListOf()
}


data class BlogModel(
    var img: Int? = null, var title: String? = null, var name: String? = null
)

class BlogBaseModel<T> : Serializable {
    @SerializedName("status")
    var statusCode: Int = 0

    @SerializedName("message")
    var message: String = ""

    @SerializedName("data")
    var dataModel: List<BlogModel>? = null
}