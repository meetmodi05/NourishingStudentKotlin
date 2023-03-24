package com.example.nourishinggeniusstudent.ui.view.setting

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivitySettingBinding
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.profile.ProfileActivity
import com.example.nourishinggeniusstudent.ui.view.subscription.EnrollmentActivity
import com.example.nourishinggeniusstudent.ui.view.subscription.GetCounsellingActivity

class SettingActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crossIcon.setOnClickListener { finish() }
        binding.profileIcon.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }
        Glide.with(this).load(getDrawable(R.drawable.img_dummy)).centerCrop().circleCrop()
            .into(binding.profileIcon)
        binding.innerSettingRl1.setOnClickListener {
            val counsellingIntent = Intent(this, GetCounsellingActivity::class.java)
            startActivity(counsellingIntent)
        }
        binding.innerSettingRl2.setOnClickListener {
            val enrollmentIntent = Intent(this, EnrollmentActivity::class.java)
            startActivity(enrollmentIntent)
        }
        binding.innerSettingRl3.setOnClickListener {
            val helpDeskIntent = Intent(this, HelpDeskActivity::class.java)
            startActivity(helpDeskIntent)
        }
        binding.innerSettingRl5.setOnClickListener {
            val feedbackIntent = Intent(this, FeedBack::class.java)
            startActivity(feedbackIntent)
        }
        binding.innerSettingRl6.setOnClickListener {
            session?.logout()
            val feedbackIntent = Intent(this, LoginActivity::class.java)
            feedbackIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(feedbackIntent)
        }
    }
}