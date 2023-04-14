package com.example.nourishinggeniusstudent.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.nourishinggeniusstudent.Adapter.*
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDashBoardBinding
import com.example.nourishinggeniusstudent.model.*
import com.example.nourishinggeniusstudent.ui.view.Blog.BlogActivity
import com.example.nourishinggeniusstudent.ui.view.Career.CareerActivity
import com.example.nourishinggeniusstudent.ui.view.CaseStudy.CaseStudiesActivity
import com.example.nourishinggeniusstudent.ui.view.Domain.DomainActivity
import com.example.nourishinggeniusstudent.ui.viewModel.Blog.BlogViewModel
import com.example.nourishinggeniusstudent.ui.viewModel.CaseStudiesViewModel
import com.example.nourishinggeniusstudent.ui.viewModel.DashboardViewModel
import com.example.nourishinggeniusstudent.ui.viewModel.Domain.DomainViewModel

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    private val blogViewModel by lazy { BlogViewModel() }
    private val domainViewModel by lazy { DomainViewModel() }
    private val caseStudiesViewModel by lazy { CaseStudiesViewModel() }
    private val dashboardViewModel by lazy { DashboardViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivMenuIcon.setOnClickListener { binding.dr1.openDrawer(GravityCompat.START) }
        binding.customLayouts.ivClose.setOnClickListener {
            binding.dr1.closeDrawer(
                GravityCompat.START, true
            )
        }
        binding.llFirst.setOnClickListener {
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
        binding.customLayouts.homeTvCl.setOnClickListener {
            val homeIntent = Intent(this, DashBoardActivity::class.java)
            startActivity(homeIntent)
        }
        binding.customLayouts.careersTvCl.setOnClickListener {
            val careerIntent = Intent(this, CareerActivity::class.java)
            startActivity(careerIntent)
        }
        binding.customLayouts.reportsTvCl.setOnClickListener {
            val reportIntent = Intent(this, IdentifyGeniusActivity::class.java)
            startActivity(reportIntent)
        }
        binding.customLayouts.counselingTvCl.setOnClickListener {
            val counsellingIntent = Intent(this, GetCounsellingActivity::class.java)
            startActivity(counsellingIntent)
        }
        binding.customLayouts.blogsTvCl.setOnClickListener {
            val blogIntent = Intent(this, BlogActivity::class.java)
            startActivity(blogIntent)
        }
        binding.customLayouts.dExpertTvCl.setOnClickListener {
            val domainIntent = Intent(this, DomainActivity::class.java)
            startActivity(domainIntent)
        }
        binding.customLayouts.caseStudiesTvCl.setOnClickListener {
            val caseStudiesIntent = Intent(this, DomainActivity::class.java)
            startActivity(caseStudiesIntent)
        }
        binding.customLayouts.settingTvCl.setOnClickListener {
            val settingIntent = Intent(this, SettingActivity::class.java)
            startActivity(settingIntent)
        }

        //Blogs RecyclerView


        blogViewModel.init()
        blogViewModel.blogList()
        setBlogAdapter()

        //Domain Expert RecyclerView

        domainViewModel.init()
        domainViewModel.getDomainList()
        setDomainAdapter()

        //Case Studies RV
//        caseStudiesViewModel.init()
//        caseStudiesViewModel.caseStudiesList()
//        setCaseStudiesAdapter()

        dashboardViewModel.init()
        dashboardViewModel.getDashboardTopExportList()
        setTopExpertAdapter()

        dashboardViewModel.getDashboardCaseStudy()
        setCaseStudiesAdapter()

        dashboardViewModel.getList()
        setDashboardDataPostAdapter()


        //Successful Model Recycler
        val successfulList = arrayListOf<SuccessfulModel>()
        successfulList.add(SuccessfulModel(R.drawable.person_outline_white, "Ratan Tata"))
        successfulList.add(SuccessfulModel(R.drawable.person_outline_white, "Dr.APJ Abdul\nKalam"))
        successfulList.add(SuccessfulModel(R.drawable.person_outline_white, "Ram\nJethmalai"))
        successfulList.add(SuccessfulModel(R.drawable.person_outline_white, "Ramanujan"))

        binding.rvSuccess.layoutManager = GridLayoutManager(this, 2)
        binding.rvSuccess.adapter = SuccessfulAdapter(this, successfulList)

        val mostPopularList = arrayListOf<MostPopularModel>()

        mostPopularList.add(
            MostPopularModel(
                R.drawable.img_1,
                R.drawable.assignment,
                getString(R.string.in_self_assessment),
                getString(R.string.why_is_career_guidence_important),
                getString(R.string.careerTxt)
            )
        )
        mostPopularList.add(
            MostPopularModel(
                R.drawable.img_1,
                R.drawable.assignment,
                getString(R.string.in_self_assessment),
                getString(R.string.why_is_career_guidence_important),
                getString(R.string.careerTxt)
            )
        )
//        binding.mostPopularRV.layoutManager = LinearLayoutManager(this, VERTICAL, false)
//        binding.mostPopularRV.adapter = MostPopularAdapter(this, mostPopularList)

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
                    this, CaseStudiesActivity::class.java
                )
            )
        }
    }

    private fun setDomainAdapter() {
        binding.rvDomain.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.rvDomain.adapter = domainViewModel.getDomainAdapter()
    }

    private fun setBlogAdapter() {
        binding.rvBlog.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.rvBlog.adapter = blogViewModel.getBlogAdapter()
    }

    private fun setCaseStudiesAdapter() {
        binding.rvCaseStudies.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.rvCaseStudies.adapter = dashboardViewModel.getCaseStudyDashboardAdapter()
    }

    private fun setTopExpertAdapter() {
        binding.rvTopExpert.layoutManager = GridLayoutManager(this, 2)
        binding.rvTopExpert.adapter = dashboardViewModel.getExportDashboardAdapter()
    }

    private fun setDashboardDataPostAdapter() {
        binding.rvMostPopular.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        binding.rvMostPopular.adapter = dashboardViewModel.getAdapter()
    }

}