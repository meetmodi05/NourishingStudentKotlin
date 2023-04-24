package com.example.nourishinggeniusstudent.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDashBoardBinding
import com.example.nourishinggeniusstudent.databinding.ActivityDashBoardNewBinding
import com.example.nourishinggeniusstudent.model.response.DashboardResponseModel
import com.example.nourishinggeniusstudent.ui.adapter.*
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyActivity
import com.example.nourishinggeniusstudent.ui.view.assessment.IdentifyGeniusActivity
import com.example.nourishinggeniusstudent.ui.view.auth.AuthViewModel
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
import com.service.taas.Activities.StartTestService
import com.service.taas.Helpers.BetterActivityResult
import java.util.Base64

class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashBoardNewBinding
    private val viewModel by lazy { DashboardViewModel(this) }
    private val caseStudyViewModel by lazy { CaseStudyViewModel(this) }
    private val domainViewModel by lazy { DomainViewModel(this) }
    private val profileViewModel by lazy { AuthViewModel(this) }


    val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
        BetterActivityResult.registerActivityForResult(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        viewModel.getDashboardData()
        profileViewModel.getuserinfobyid(session?.user?.userId.toString())
        setClickListeners()
        setObservers()
        /*Glide.with(this).load(
            if (session?.user?.profilePic.isNullOrBlank()) {
                session?.user?.profilePic
            } else R.drawable.round_person_28
        ).centerCrop().placeholder(getDrawable(R.drawable.round_person_28)).centerCrop()
            .into(binding.ivPersonIcon)*/
        binding.tvStudentName.text = session?.user?.name
    }

    private fun setObservers() {
        viewModel.dashboardData.observe(this) {
            setAdapters(it)
            viewModel.isLoading.value = false
        }
        viewModel.subscribeToNewsletter.observe(this) {
            showToast(it)
            binding.etEmail.setText("")
        }
        profileViewModel.userData.observe(this) {
            session?.user = it
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

//        binding.rvTopExpert.layoutManager = GridLayoutManager(this, 2)
        val domainAdapter = DomainAdapter {
            val intent = Intent(this@DashBoardActivity, DomainDetailsActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataDexperts?.dexpertsPosts?.let { domainAdapter.setList(it) }
        binding.rvTopExpert.adapter = domainAdapter

//        binding.rvSuccess.layoutManager = GridLayoutManager(this, 2)
        val caseStudyAdapter = CaseStudyAdapter {
            val intent = Intent(this@DashBoardActivity, CaseStudyActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        model.dataCareers?.casestudysPosts?.let { caseStudyAdapter.setList(it) }
        binding.rvSuccess.adapter = caseStudyAdapter
    }

    private fun setClickListeners() {
        /*binding.ivMenuIcon.setOnClickListener { binding.dr1.openDrawer(GravityCompat.START) }
        binding.customLayouts.ivClose.setOnClickListener {
            binding.dr1.closeDrawer(
                GravityCompat.START, true
            )
        }*/
        binding.llFirst.setOnClickListener {
//            val careerIntent = Intent(this, DegreeList::class.java)
            val careerIntent = Intent(this, CareerActivity::class.java)
            startActivity(careerIntent)
        }
        binding.secondLL2.setOnClickListener {
            if (session?.user?.isSubscribed == true) {
                val name64: String =
                    Base64.getEncoder().encodeToString(session?.user?.name?.toByteArray())
                val email64: String =
                    Base64.getEncoder().encodeToString(session?.user?.email?.toByteArray())
                val testCode64: String =
                    Base64.getEncoder().encodeToString("5450700001".toByteArray())

                activityLauncher.setOnActivityResult {
                    Log.e(TAG, "onCreate: $it")
                }

                StartTestService.callTestLinkServiceForData(
                    this,
                    Constants.THINK_EXAM_CLIENT_URL,
                    name64,
                    email64,
                    "Career Guidance Program",
                    "",
                    "",
                    "120",
                    session?.user?.profilePic,
                    testCode64,
                    activityLauncher
                )
            } else {
                val intent = Intent(this, IdentifyGeniusActivity::class.java)
                startActivity(intent)
            }
        }
        binding.llThird.setOnClickListener {
            val thirdLayoutIntent = Intent(this, GetCounsellingActivity::class.java)
            startActivity(thirdLayoutIntent)
        }
        binding.ivPersonIcon.setOnClickListener {
            /*val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)*/
            val settingIntent = Intent(this, SettingActivity::class.java)
            startActivity(settingIntent)
        }
        /*binding.customLayouts.innerLl1.setOnClickListener {
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
        }*/

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
            if (!binding.etEmail.text.toString().isEmail()) {
                binding.etEmail.error = "Invalid Email"
                return@setOnClickListener
            }
            viewModel.isLoading.value = true
            viewModel.subscribeToNewsletter(binding.etEmail.text.toString())
        }
    }

    companion object {
        private val TAG = this::class.java.name
    }

}