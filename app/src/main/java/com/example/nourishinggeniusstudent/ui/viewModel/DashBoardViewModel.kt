package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.usecases.BlogUseCase
import com.example.nourishinggeniusstudent.model.Blog.BlogDataModel
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import com.example.nourishinggeniusstudent.model.MostPopularModel

class DashBoardViewModel : BaseViewModel() {
    private var blogList: ArrayList<MostPopularModel> = arrayListOf()
    private var blogLiveData: MutableLiveData<BlogDataModel> = MutableLiveData()
//    private var blogAdapter: BlogAdapter = BlogAdapter(blogList)

//    private val dashBordUseCase by lazy { BlogUseCase(errorLiveData,blogLiveData) }
}
