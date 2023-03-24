package com.example.nourishinggeniusstudent.model.data

import com.google.gson.annotations.SerializedName

class BlogDataModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("post_name") var postName: String? = null,
    @SerializedName("post_image_url") var postImageUrl: String? = null
):java.io.Serializable