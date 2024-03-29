package com.example.nourishinggeniusstudent.Networking.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Networking {
    private val BASEURL = "https://g2a.8e7.myftpupload.com/wp-json/"


    fun getRetrofit(): ApiInterface {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(15, TimeUnit.SECONDS)
        httpClient.connectTimeout(15, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        //GSON converter
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ItemTypeAdapterFactory())
            .create()

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build().create(ApiInterface::class.java)
    }

    companion object {
        fun with(): Networking {
            return Networking()
        }
    }
}