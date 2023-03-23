package com.example.nourishinggeniusstudent.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.Adapter.BlogAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityBlogBinding
import com.example.nourishinggeniusstudent.model.MostPopularModel

class BlogActivity : AppCompatActivity() {
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
                R.drawable.assignment,
                getString(R.string.why_is_career_guidence_important),
                getString(R.string.in_self_assessment),
                getString(R.string.careerTxt)
            )
        )


        binding.rvBLogList.layoutManager = LinearLayoutManager(this)
        binding.rvBLogList.adapter = BlogAdapter(this,blogList)
    }
}