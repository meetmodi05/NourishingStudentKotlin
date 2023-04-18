package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody.Companion.toRequestBody

class BlogUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var blogsList: MutableLiveData<List<BlogDataModel>>? = null,
    private var blogData: MutableLiveData<BlogDataModel>? = null,
) {

    fun getBlogs() {
        if (blogsList == null) return
        Networking.with(context).getServices().getBlogs().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<BlogDataModel>>>() {
                override fun onSuccess(response: BaseModel<List<BlogDataModel>>) {
                    blogsList?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getBlogData(id: Int) {
        if (blogsList == null) return
        Networking.with(context).getServices().getBlogData(id.toString().toRequestBody())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<BlogDataModel>>() {
                override fun onSuccess(response: BaseModel<BlogDataModel>) {
                    blogData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}