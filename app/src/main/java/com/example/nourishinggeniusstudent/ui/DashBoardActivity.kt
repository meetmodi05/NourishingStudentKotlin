package com.example.nourishinggeniusstudent.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.nourishinggeniusstudent.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivMenuIcon.setOnClickListener { binding.dr1.openDrawer(GravityCompat.START) }
        binding.customLayouts.ivClose.setOnClickListener {
            binding.dr1.closeDrawer(
                GravityCompat.START,
                true
            )
        }
        binding.llFirst.setOnClickListener {
            val careerIntent = Intent(this, com.example.nourishinggeniusstudent.ui.CareerActivity::class.java)
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
    }
}