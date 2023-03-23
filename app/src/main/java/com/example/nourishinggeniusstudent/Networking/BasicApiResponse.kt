package com.example.nourishinggeniusstudent.Networking

data class BasicApiResponse<T>(
    val message: String? = null,
    val successful: Boolean,
    val data: T? = null
)