package com.example.nourishinggeniusstudent.ui.view.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityBlogDetailsBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.example.nourishinggeniusstudent.utils.getImageProgress

class BlogDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityBlogDetailsBinding
    private val viewModel by lazy { BlogViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.blogDetails.observe(this) {
            binding.tvMiniTitle.text = it.postName
            binding.tvDescription.text =
                Html.fromHtml(it.postContent, HtmlCompat.FROM_HTML_MODE_LEGACY)
            Glide.with(this@BlogDetailsActivity).load(it.postImageUrl).placeholder(getImageProgress()).into(binding.imageView)
            viewModel.isLoading.value = false
        }
    }

    private fun setClickListener() {
        binding.close.setOnClickListener { finish() }
    }

    private fun initView() {
        viewModel.getblogDetails(intent.getIntExtra(Constants.BLOG_ID,0))
    }

}