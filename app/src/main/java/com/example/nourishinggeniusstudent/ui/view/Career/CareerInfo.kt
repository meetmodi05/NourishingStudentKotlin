package com.example.nourishinggeniusstudent.ui.view.Career


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityCareerInfoBinding
import com.example.nourishinggeniusstudent.ui.viewModel.Career.CareerInfoViewModel

class CareerInfo : AppCompatActivity() {
    private lateinit var binding: ActivityCareerInfoBinding
    private val viewModel by lazy { CareerInfoViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

        viewModel.init()
        viewModel.getCareerInfoList()
    }
}