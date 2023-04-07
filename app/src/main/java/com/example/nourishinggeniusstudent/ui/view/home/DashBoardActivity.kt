package com.example.nourishinggeniusstudent.ui.view.home

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDashBoardBinding
import com.example.nourishinggeniusstudent.model.response.DashboardResponseModel
import com.example.nourishinggeniusstudent.ui.adapter.*
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyActivity
import com.example.nourishinggeniusstudent.ui.view.assessment.IdentifyGeniusActivity
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogActivity
import com.example.nourishinggeniusstudent.ui.view.blog.BlogDetailsActivity
import com.example.nourishinggeniusstudent.ui.view.career.CareerActivity
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyViewModel
import com.example.nourishinggeniusstudent.ui.view.caseStudy.DomainDetailsActivity
import com.example.nourishinggeniusstudent.ui.view.domain.DomainActivity
import com.example.nourishinggeniusstudent.ui.view.domain.DomainViewModel
import com.example.nourishinggeniusstudent.ui.view.profile.ProfileActivity
import com.example.nourishinggeniusstudent.ui.view.setting.SettingActivity
import com.example.nourishinggeniusstudent.ui.view.subscription.GetCounsellingActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.example.nourishinggeniusstudent.utils.isEmail
import com.example.nourishinggeniusstudent.utils.showToast

class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    private val viewModel by lazy { DashboardViewModel(this) }
    private val caseStudyViewModel by lazy { CaseStudyViewModel(this) }
    private val domainViewModel by lazy { DomainViewModel(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        viewModel.getDashboardData()
        setClickListeners()
        setObservers()
        Glide.with(this).load(
            if (session?.user?.profilePic.isNullOrBlank()) {
                session?.user?.profilePic
            } else R.drawable.round_person_28
        ).centerCrop().placeholder(getDrawable(R.drawable.round_person_28)).centerCrop()
            .into(binding.ivPersonIcon)
    }

    private fun setObservers() {
        viewModel.dashboardData.observe(this) {
            setAdapters(it)
            viewModel.isLoading.value = false
        }
        viewModel.subscribeToNewsletter.observe(this) {
            showToast(it)
            binding.mailET.setText("")
        }
    }

    private fun setAdapters(model: DashboardResponseModel) {
        binding.mostPopularRV.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.mostPopularRV.adapter = model.dataPost?.defaultPosts?.let {
            BlogAdapter(it) { data ->
                val intent = Intent(this@DashBoardActivity, BlogDetailsActivity::class.java)
                intent.putExtra(Constants.BLOG_ID, data.id)
                startActivity(intent)
            }
        }

        binding.rvTopExpert.layoutManager = GridLayoutManager(this, 2)
        val domainAdapter = DomainAdapter {
            val intent = Intent(this@DashBoardActivity, DomainDetailsActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataDexperts?.dexpertsPosts?.let { domainAdapter.setList(it) }
        binding.rvTopExpert.adapter = domainAdapter

        binding.rvSuccess.layoutManager = GridLayoutManager(this, 2)
        val caseStudyAdapter = CaseStudyAdapter {
            val intent = Intent(this@DashBoardActivity, CaseStudyActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataCareers?.casestudysPosts?.let { caseStudyAdapter.setList(it) }
        binding.rvSuccess.adapter = caseStudyAdapter
    }

    private fun setClickListeners() {
        binding.ivMenuIcon.setOnClickListener { binding.dr1.openDrawer(GravityCompat.START) }
        binding.customLayouts.ivClose.setOnClickListener {
            binding.dr1.closeDrawer(
                GravityCompat.START, true
            )
        }
        binding.llFirst.setOnClickListener {
//            val careerIntent = Intent(this, DegreeList::class.java)
            val careerIntent = Intent(this, CareerActivity::class.java)
            startActivity(careerIntent)
        }
        binding.secondLL2.setOnClickListener {
            val intent = Intent(this, IdentifyGeniusActivity::class.java)
            startActivity(intent)
        }
        binding.llThird.setOnClickListener {
            val thirdLayoutIntent = Intent(this, GetCounsellingActivity::class.java)
            startActivity(thirdLayoutIntent)
        }
        binding.ivPersonIcon.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
        binding.customLayouts.innerLl1.setOnClickListener {
            val homeIntent = Intent(this, DashBoardActivity::class.java)
            startActivity(homeIntent)
        }
        binding.customLayouts.innerLl2.setOnClickListener {
            val careerIntent = Intent(this, CareerActivity::class.java)
            startActivity(careerIntent)
        }
        binding.customLayouts.innerLl3.setOnClickListener {
            val reportIntent = Intent(this, IdentifyGeniusActivity::class.java)
            startActivity(reportIntent)
        }
        binding.customLayouts.innerLl4.setOnClickListener {
            val counsellingIntent = Intent(this, GetCounsellingActivity::class.java)
            startActivity(counsellingIntent)
        }
        binding.customLayouts.innerLl5.setOnClickListener {
            val blogIntent = Intent(this, BlogActivity::class.java)
            startActivity(blogIntent)
        }
        binding.customLayouts.innerLl6.setOnClickListener {
            val domainIntent = Intent(this, DomainActivity::class.java)
            startActivity(domainIntent)
        }
        binding.customLayouts.innerLl7.setOnClickListener {
            val caseStudiesIntent = Intent(this, CaseStudyActivity::class.java)
            startActivity(caseStudiesIntent)
        }
        binding.customLayouts.innerLl9.setOnClickListener {
            val settingIntent = Intent(this, SettingActivity::class.java)
            startActivity(settingIntent)
        }

        binding.tvSeeAll.setOnClickListener {
            startActivity(
                Intent(
                    this, BlogActivity::class.java
                )
            )
        }
        binding.tvSeeAll2.setOnClickListener {
            startActivity(
                Intent(
                    this, DomainActivity::class.java
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
        binding.tvSubscribe.setOnClickListener {
            if (!binding.mailET.text.toString().isEmail()) {
                binding.mailET.error = "Invalid Email"
                return@setOnClickListener
            }
            viewModel.isLoading.value = true
            viewModel.subscribeToNewsletter(binding.mailET.text.toString())
        }
    }
}