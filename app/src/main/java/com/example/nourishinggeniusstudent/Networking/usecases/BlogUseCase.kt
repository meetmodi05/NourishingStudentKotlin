package com.example.nourishinggeniusstudent.Networking.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.BlogRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.Blog.BlogDataModel
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var blogLiveData: MutableLiveData<List<BlogModel>>? = null
) {
    private val blogRepo by lazy { BlogRepo() }

    fun getBlogList() {
        if (blogLiveData == null) return

        blogRepo.blogDetail().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<BlogDataModel>>() {
                override fun onSuccess(response: BaseModel<BlogDataModel>) {
                    Log.d("Success", "onSuccess: " + response.message + response.statusCode)
                    blogLiveData!!.postValue(response.dataModel?.data)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                    Log.e("Failed", "onFailed: $message$code")
                }
            })
    }
}