package com.example.nourishinggeniusstudent.Networking.repo

import com.example.nourishinggeniusstudent.Networking.network.Networking
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.Blog.BlogDataModel
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class BlogRepo {
    fun blogDetail(): Observable<BaseModel<List<BlogModel>>> {
        val map = HashMap<String, RequestBody>()
        map["role"] = toRequestBody("student")
//        map["search_term"]=toRequestBody("hello")
//        map["tax_id"]=toRequestBody("1")
        return Networking.with().getRetrofit().sendBlogList(map)
    }

    private fun toRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }
}