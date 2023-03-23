package com.example.nourishinggeniusstudent.Networking

import android.database.Observable
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegisterApiInterface {
    @Multipart
    @POST("auth/register")
    suspend fun createUser(
        @Part userData:MultipartBody.Part):Observable<SignUpModel>
}