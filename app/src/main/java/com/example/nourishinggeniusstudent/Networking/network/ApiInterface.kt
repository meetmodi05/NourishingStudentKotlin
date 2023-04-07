package com.example.nourishinggeniusstudent.Networking.network


import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.BlogModel
import com.example.nourishinggeniusstudent.model.CareerDetail
import com.example.nourishinggeniusstudent.model.DataModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {
    @Multipart
    @POST("auth/register")
    fun createUser(@PartMap userData: MutableMap<String, RequestBody>): Observable<BaseModel<SignUpModel>>

    @Multipart
    @POST("career/listing")
    fun sendCareerList(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<DataModel>>

    @Multipart
    @POST("career/details")
    fun sendCarerDetails(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<CareerDetail>>

    @Multipart
    @POST("blog/listing")
    fun sendBlogList(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<BlogModel>>
}