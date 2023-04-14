package com.example.nourishinggeniusstudent.ui.view.CaseStudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityCaseStudiesBinding
import com.example.nourishinggeniusstudent.ui.viewModel.CaseStudiesViewModel

class CaseStudiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCaseStudiesBinding
    private val viewModel by lazy { CaseStudiesViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaseStudiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }
        viewModel.init()
        viewModel.caseStudiesList()
        setAdapter()
    }

    private fun setAdapter() {
        binding.rvCaseStudies.layoutManager = GridLayoutManager(this, 2)
        binding.rvCaseStudies.adapter = viewModel.caseStudiesAdapter()
    }
}