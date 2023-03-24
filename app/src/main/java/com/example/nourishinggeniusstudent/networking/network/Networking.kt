package com.example.nourishinggeniusstudent.networking.network

import android.content.Context
import com.example.nourishinggeniusstudent.utils.Session
import com.google.gson.GsonBuilder
import com.example.nourishinggeniusstudent.networking.network.APIInterface
import com.example.nourishinggeniusstudent.networking.network.ItemTypeAdapterFactory
import com.example.nourishinggeniusstudent.networking.network.SessionInterceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class Networking(private val context: Context? = null) {
    private var baseURL: String = "https://g2a.8e7.myftpupload.com/wp-json/"

    companion object {

        var session: Session? = null

        fun with(context: Context? = null): Networking {
            session = context?.let { Session(it) }
            return Networking(context)
        }
    }

    fun getServices(timeOutInMinutes: Long = 2): APIInterface {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(timeOutInMinutes, TimeUnit.MINUTES)
        httpClient.connectTimeout(timeOutInMinutes, TimeUnit.MINUTES)

        httpClient.addInterceptor(SessionInterceptor(context!!))

        //Log
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        //GSON converter
        val gson = GsonBuilder().registerTypeAdapterFactory(ItemTypeAdapterFactory()).create()

        return retrofit2.Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(httpClient.build())
            .build().create(APIInterface::class.java)
    }
}

