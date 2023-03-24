package com.example.nourishinggeniusstudent.networking.network

import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.careers.CareerDetails
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.model.response.CareersListResponseModel
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.*

interface APIInterface {

    @Multipart
    @POST("auth/register")
    fun register(
        @Part("email_address") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("role") role: RequestBody,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("auth/login")
    fun login(
        @Part("username") email: RequestBody,
        @Part("password") password: RequestBody,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("career/listing")
    fun getCareers(
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<CareersListResponseModel>>

    @Multipart
    @POST("career/details")
    fun getCareerDetails(
        @Part("career_id") careerId: RequestBody,
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<CareerDetails>>

    @Multipart
    @POST("blog/listing")
    fun getBlogs(
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<List<BlogDataModel>>>

    @Multipart
    @POST("blog/details")
    fun getBlogData(
        @Part("id") id: RequestBody,
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<BlogDataModel>>


}
