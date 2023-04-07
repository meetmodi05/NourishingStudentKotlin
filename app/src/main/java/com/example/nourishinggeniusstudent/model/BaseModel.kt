package com.example.nourishinggeniusstudent.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseModel<T> : Serializable {
    @SerializedName("status")
    var statusCode: Int = 0

    @SerializedName("message")
    var message: String = ""

    @SerializedName("data")
    var dataModel: T? = null
}
