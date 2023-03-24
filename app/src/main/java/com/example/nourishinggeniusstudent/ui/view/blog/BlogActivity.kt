package com.example.nourishinggeniusstudent.ui.view.blog

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.ui.adapter.BlogAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityBlogBinding
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.model.data.BlogModel
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.Constants

class BlogActivity : BaseActivity() {
    private lateinit var binding: ActivityBlogBinding
    private val viewModel by lazy { BlogViewModel(this@BlogActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setObservers()
        setClickListeners()
        setDropDown()
    }

    private fun setObservers() {
        viewModel.blogsList.observe(this) {
            setAdapter(it as ArrayList<BlogDataModel>)
            viewModel.isLoading.value = false
        }
    }

    private fun initView() {
        viewModel.isLoading.value = true
        viewModel.getblogs()
    }

    private fun setClickListeners() {
        binding.backAeroIcon.setOnClickListener { finish() }
    }

    private fun setDropDown() {
        val dropList = arrayListOf<String>()
        dropList.add("Careers")
        dropList.add("Discipline")
        dropList.add("Self Assessment")
        dropList.add("Productivity")

        val dropAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropList)
        binding.autoCompleteTV.setAdapter(dropAdapter)

    }

    fun setAdapter(blogList: ArrayList<BlogDataModel>) {
        binding.rvBLogList.adapter = BlogAdapter(blogList) {
            val intent = Intent(this@BlogActivity, BlogDetailsActivity::class.java)
            intent.putExtra(Constants.BLOG_ID, it.id)
            startActivity(intent)
        }
    }

}