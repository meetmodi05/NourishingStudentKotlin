package com.example.nourishinggeniusstudent.ui.viewModel.Dashboard

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.Blog.BlogDataModel
import com.example.nourishinggeniusstudent.model.MostPopularModel
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class DashBoardViewModel : BaseViewModel() {
    private var blogList: ArrayList<MostPopularModel> = arrayListOf()
    private var blogLiveData: MutableLiveData<BlogDataModel> = MutableLiveData()
//    private var blogAdapter: BlogAdapter = BlogAdapter(blogList)

//    private val dashBordUseCase by lazy { BlogUseCase(errorLiveData,blogLiveData) }
}
