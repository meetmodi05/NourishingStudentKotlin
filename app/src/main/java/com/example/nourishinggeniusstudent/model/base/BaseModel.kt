package com.example.nourishinggeniusstudent.model.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseModel<T> : Serializable {

    @SerializedName("message")
    var message: String = ""

    @SerializedName("status")
    var status: Int? = null

    @SerializedName("data")
    var data: T? = null
}
