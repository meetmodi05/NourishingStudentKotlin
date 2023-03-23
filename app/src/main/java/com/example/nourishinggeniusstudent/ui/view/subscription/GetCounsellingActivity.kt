package com.example.nourishinggeniusstudent.ui.view.subscription

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityGetCounsellingBinding
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.ui.adapter.PackageAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class GetCounsellingActivity : BaseActivity() {
    private lateinit var binding: ActivityGetCounsellingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCounsellingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }

        val packageList = arrayListOf<PackagesModel>()
        packageList.add(
            PackagesModel(
                getString(R.string.career_selection_nrs_3999),
                getString(R.string.psychometric_assessment_report),
                getString(R.string._2_counseling_1_to_1_sessions_with_career_counselor)
            )
        )
        packageList.add(
            PackagesModel(
                getString(R.string.career_selection_nrs_3999),
                getString(R.string.psychometric_assessment_report),
                getString(R.string._2_counseling_1_to_1_sessions_with_career_counselor)
            )
        )

        binding.rvPackages.layoutManager = LinearLayoutManager(this)
        binding.rvPackages.adapter = PackageAdapter(this, packageList)
    }
}