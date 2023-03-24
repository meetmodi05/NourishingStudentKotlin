package com.example.nourishinggeniusstudent.ui.view.blog

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.networking.usecases.BlogUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class BlogViewModel(mContext: Context) : BaseViewModel(mContext) {

    var blogsList: MutableLiveData<List<BlogDataModel>> = MutableLiveData()
    var blogDetails: MutableLiveData<BlogDataModel> = MutableLiveData()
    private var usecase = BlogUsecase(mContext, errorLiveData, blogsList, blogDetails)

    fun getblogs() = usecase.getBlogs()
    fun getblogDetails(blogId: Int) = usecase.getBlogData(blogId)

}