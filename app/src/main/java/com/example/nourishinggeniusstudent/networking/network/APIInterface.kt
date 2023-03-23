package com.example.nourishinggeniusstudent.networking.network

import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface APIInterface {

    @Multipart
    @POST("auth/register")
    fun register(
        @Part("email_address") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("role") role: RequestBody,
    ): Observable<BaseModel<User>>


}
