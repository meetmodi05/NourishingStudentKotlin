package com.example.nourishinggeniusstudent.Networking

import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.model.CareerModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface ApiInterface {
    @Multipart
    @POST("auth/register")
    fun createUser(@PartMap userData: MutableMap<String, RequestBody>): Call<SignUpModel>

    @Multipart
    @GET("career/listing")
    fun getCareerList(): Call<CareerModel>
}