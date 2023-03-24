package com.example.nourishinggeniusstudent.model.data

import com.google.gson.annotations.SerializedName

class BlogDataModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("post_name", alternate = ["post_title"]) var postName: String? = null,
    @SerializedName("post_content") var postContent: String? = null,
    @SerializedName("post_image_url", alternate = ["post_image_uri"]) var postImageUrl: String? = null
):java.io.Serializable