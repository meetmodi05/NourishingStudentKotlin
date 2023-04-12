package com.example.nourishinggeniusstudent.Networking.repo

import com.example.nourishinggeniusstudent.Networking.network.Networking
import com.example.nourishinggeniusstudent.model.DashboardModel
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class DashboardRepo {
    fun getDashboardList(): Observable<DashboardModel> {
        val map = HashMap<String, RequestBody>()
        map["role"] = toRequestBody("student")
        return Networking.with().getRetrofit().dashboardList(map)
    }

    private fun toRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }
}