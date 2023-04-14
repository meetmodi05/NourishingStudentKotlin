package com.example.nourishinggeniusstudent.ui.view.Blog

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.Adapter.BlogAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityBlogBinding
import com.example.nourishinggeniusstudent.model.MostPopularModel
import com.example.nourishinggeniusstudent.ui.viewModel.Blog.BlogViewModel

class BlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlogBinding
    private val blogViewModel by lazy { BlogViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

        val dropList = arrayListOf<String>()
        dropList.add("Careers")
        dropList.add("Discipline")
        dropList.add("Self Assessment")
        dropList.add("Productivity")

        val dropAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropList)
        binding.autoCompleteTV.setAdapter(dropAdapter)

        blogViewModel.init()
        blogViewModel.blogList()
        setAdapter()


    }

    private fun setAdapter() {
        binding.rvBLogList.layoutManager = GridLayoutManager(this, 2)
        binding.rvBLogList.adapter = blogViewModel.getBlogAdapter()
    }
}