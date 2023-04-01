package com.example.nourishinggeniusstudent.ui.view.caseStudy

import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.ActivityBlogDetailsBinding
import com.example.nourishinggeniusstudent.databinding.ActivityCaseStudyDetailsBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogViewModel
import com.example.nourishinggeniusstudent.utils.Constants

class CaseStudyDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityCaseStudyDetailsBinding
    private val viewModel by lazy { CaseStudyViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaseStudyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.caseStudyData.observe(this) {
            binding.tvMiniTitle.text = it.title
            binding.tvDescription.text =
                Html.fromHtml(it.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
            Glide.with(this@CaseStudyDetailsActivity).load(it.featureUri).into(binding.imageView)
            viewModel.isLoading.value = false
        }
    }

    private fun setClickListener() {
        binding.close.setOnClickListener { finish() }
    }

    private fun initView() {
        viewModel.getCaseStudyData(intent.getIntExtra(Constants.CASE_STUDY_ID,0))
    }

}