package com.example.nourishinggeniusstudent.Networking.repo

import com.example.nourishinggeniusstudent.Networking.network.Networking
import com.example.nourishinggeniusstudent.model.DashboardModel
import com.example.nourishinggeniusstudent.model.DataCareers
import com.example.nourishinggeniusstudent.model.DataDomainExport
import com.example.nourishinggeniusstudent.model.DataPost
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class DashboardRepo {
    fun getDashboardList(): Observable<DashboardModel<DataPost>> {
        val map = HashMap<String, RequestBody>()
        map["role"] = toRequestBody("student")
        return Networking.with().getRetrofit().dashboardDataPost(map)
    }

    fun getDashboardDomain(): Observable<DashboardModel<DataDomainExport>> {
        val map = HashMap<String, RequestBody>()
        map["role"] = toRequestBody("student")
        return Networking.with().getRetrofit().dashboardDataDomain(map)
    }

    fun getDashboardCaseStudy(): Observable<DashboardModel<DataCareers>> {
        val map = HashMap<String, RequestBody>()
        map["role"] = toRequestBody("student")
        return Networking.with().getRetrofit().dashboardCaseStudy(map)
    }

    private fun toRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }
}