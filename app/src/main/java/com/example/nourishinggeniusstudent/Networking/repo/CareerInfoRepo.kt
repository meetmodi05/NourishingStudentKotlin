package com.example.nourishinggeniusstudent.Networking.repo

import com.example.nourishinggeniusstudent.Networking.network.Networking
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.CareerInfoDetail
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class CareerInfoRepo {
    fun careerInfo(): Observable<BaseModel<CareerInfoDetail>> {
        val map = mutableMapOf<String, RequestBody>()
        map["role"] = toRequestBody("student")
        map["career_id"] = toRequestBody("140")
        return Networking.with().getRetrofit().sendCarerInfo(map)
    }

    private fun toRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }
}