package com.example.nourishinggeniusstudent.ui.view.Career

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityCareerBinding
import com.example.nourishinggeniusstudent.ui.viewModel.Career.CareerViewModel

class CareerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerBinding
    private val viewModel by lazy { CareerViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

        if (savedInstanceState == null) viewModel.init()
        viewModel.getCareerList()
        setAdapter()

    }

    private fun setAdapter() {
        binding.circularProgress.visibility = View.GONE
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter = viewModel.getCareerAdapter()
    }
}