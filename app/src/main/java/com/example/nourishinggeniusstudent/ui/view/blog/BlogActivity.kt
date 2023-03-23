package com.example.nourishinggeniusstudent.ui.view.blog

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.ui.adapter.BlogAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityBlogBinding
import com.example.nourishinggeniusstudent.model.data.MostPopularModel
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class BlogActivity : BaseActivity() {
    private lateinit var binding: ActivityBlogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

        val blogList = arrayListOf<MostPopularModel>()
        //ArrayList

        blogList.add(
            MostPopularModel(
                R.drawable.img_1,
                getString(R.string.why_is_career_guidence_important),
                getString(R.string.in_self_assessment),
                getString(R.string.careerTxt)
            )
        )


        binding.rvBLogList.layoutManager = LinearLayoutManager(this)
        binding.rvBLogList.adapter = BlogAdapter(this, blogList)

        val dropList = arrayListOf<String>()
        dropList.add("Careers")
        dropList.add("Discipline")
        dropList.add("Self Assessment")
        dropList.add("Productivity")

        val dropAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropList)
        binding.autoCompleteTV.setAdapter(dropAdapter)
    }
}