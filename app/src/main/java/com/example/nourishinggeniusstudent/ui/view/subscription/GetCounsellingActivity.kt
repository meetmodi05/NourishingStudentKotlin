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
    private val viewModel by lazy { SubscriptionViewModel(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCounsellingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setClickListners()
        setObservers()
    }

    private fun setClickListners() {
        binding.backAeroIcon.setOnClickListener { finish() }
    }

    private fun setObservers() {
        viewModel.packages.observe(this) {
            binding.rvPackages.layoutManager = LinearLayoutManager(this)
            binding.rvPackages.adapter = PackageAdapter(this, it.packagesPosts)
            viewModel.isLoading.value = false
        }
    }

    private fun initView() {
        viewModel.getSubscriptionPackages()
    }


}