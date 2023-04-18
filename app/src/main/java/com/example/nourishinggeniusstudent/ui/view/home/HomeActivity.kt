package com.example.nourishinggeniusstudent.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityHomeBinding
import com.example.nourishinggeniusstudent.model.response.DashboardResponseModel
import com.example.nourishinggeniusstudent.ui.adapter.BlogAdapter
import com.example.nourishinggeniusstudent.ui.adapter.CaseStudyAdapter
import com.example.nourishinggeniusstudent.ui.adapter.DomainAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogDetailsActivity
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyActivity
import com.example.nourishinggeniusstudent.ui.view.caseStudy.DomainDetailsActivity
import com.example.nourishinggeniusstudent.ui.view.domain.DomainActivity
import com.example.nourishinggeniusstudent.utils.Constants

class HomeActivity : BaseActivity() {
    lateinit var binding: ActivityHomeBinding

    //    private val viewModel by lazy { BlogViewModel(this@HomeActivity) }
    private val viewModel by lazy { DashboardViewModel(this@HomeActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivMenuIconHome.setOnClickListener { binding.drawerHome.openDrawer(Gravity.LEFT) }
        binding.customLayouts.ivClose.setOnClickListener {
            binding.drawerHome.closeDrawer(
                GravityCompat.END, true
            )
        }
        binding.tvBlogSeeAll.setOnClickListener {
            startActivity(
                Intent(
                    this, BlogActivity::class.java
                )
            )
        }
        binding.tvExplore.setOnClickListener {
            startActivity(
                Intent(
                    this, CaseStudyActivity::class.java
                )
            )
        }
        binding.tvExportAll.setOnClickListener {
            startActivity(
                Intent(
                    this, DomainActivity::class.java
                )
            )
        }
        viewModel.getDashboardData()
        setObservers()

    }

    private fun setObservers() {
        viewModel.dashboardData.observe(this) {
            setAdapters(it)
            viewModel.isLoading.value = false
        }
    }


    private fun setAdapters(model: DashboardResponseModel) {
        binding.rvBlogsHome.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBlogsHome.adapter = model.dataPost?.defaultPosts?.let {
            BlogAdapter(it) { data ->
                val intent = Intent(this@HomeActivity, BlogDetailsActivity::class.java)
                intent.putExtra(Constants.BLOG_ID, data.id)
                startActivity(intent)
            }
        }


        binding.rvTopExportHome.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val domainAdapter = DomainAdapter {
            val intent = Intent(this@HomeActivity, DomainDetailsActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataDexperts?.dexpertsPosts?.let { domainAdapter.setList(it) }
        binding.rvTopExportHome.adapter = domainAdapter

        binding.rvCaseStudiesHome.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val caseStudyAdapter = CaseStudyAdapter {
            val intent = Intent(this@HomeActivity, CaseStudyActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataCareers?.casestudysPosts?.let { caseStudyAdapter.setList(it) }
        binding.rvCaseStudiesHome.adapter = caseStudyAdapter

        binding.rvSuccess.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSuccess.adapter = caseStudyAdapter
    }


}