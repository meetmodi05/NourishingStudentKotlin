package com.example.nourishinggeniusstudent.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityGetCounsellingBinding
import com.example.nourishinggeniusstudent.ui.viewModel.Packages.PackageViewModel

class GetCounsellingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetCounsellingBinding
    private val viewModel by lazy { PackageViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCounsellingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }
        viewModel.init()
        viewModel.packagesList()
        setAdapter()


/*
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
*/

    }

    private fun setAdapter() {
        binding.rvPackages.layoutManager = LinearLayoutManager(this)
        binding.rvPackages.adapter = viewModel.getPackagesAdapter()
    }
}