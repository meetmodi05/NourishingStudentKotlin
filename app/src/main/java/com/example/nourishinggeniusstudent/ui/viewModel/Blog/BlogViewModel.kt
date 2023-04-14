package com.example.nourishinggeniusstudent.ui.viewModel.Blog

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.BlogAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.BlogUseCase
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class BlogViewModel : BaseViewModel() {
    private var blogList: MutableList<BlogModel> = mutableListOf()
    private var blogLiveData: MutableLiveData<List<BlogModel>> = MutableLiveData()
    private var blogAdapter: BlogAdapter = BlogAdapter(blogList)

    private val blogUseCase by lazy {
        BlogUseCase(errorLiveData, blogLiveData)
    }

    fun init() {
        blogAdapter = BlogAdapter(blogList)
        blogLiveData.observeForever {
            if (!it.isNullOrEmpty()) {
                blogList.clear()
                blogList.addAll(it)
                blogAdapter.notifyDataSetChanged()

                println("++++++++++careerList++++++++++$blogList")
            } else {
                println("List Has no rec")
            }
        }
    }

    fun blogList() = blogUseCase.getBlogList()
    fun getBlogAdapter(): BlogAdapter = blogAdapter
}