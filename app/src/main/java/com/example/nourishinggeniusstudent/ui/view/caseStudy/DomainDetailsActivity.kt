package com.example.nourishinggeniusstudent.ui.view.caseStudy

import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.ActivityBlogDetailsBinding
import com.example.nourishinggeniusstudent.databinding.ActivityCaseStudyDetailsBinding
import com.example.nourishinggeniusstudent.databinding.ActivityDomainDetailsBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogViewModel
import com.example.nourishinggeniusstudent.ui.view.domain.DomainViewModel
import com.example.nourishinggeniusstudent.utils.Constants

class DomainDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDomainDetailsBinding
    private val viewModel by lazy { DomainViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomainDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.domainData.observe(this) {
            binding.tvMiniTitle.text = it.title
            binding.tvSubTitle.text = it.position
//            binding.tvDescription.text = Html.fromHtml(it.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
            Glide.with(this@DomainDetailsActivity).load(it.featureUri).into(binding.imageView)
            viewModel.isLoading.value = false
        }
    }

    private fun setClickListener() {
        binding.close.setOnClickListener { finish() }
    }

    private fun initView() {
        viewModel.getDomainData(intent.getIntExtra(Constants.CASE_STUDY_ID, 0))
    }

}