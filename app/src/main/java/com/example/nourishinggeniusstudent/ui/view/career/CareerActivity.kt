package com.example.nourishinggeniusstudent.ui.view.career

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.ui.adapter.CareerAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityCareerBinding
import com.example.nourishinggeniusstudent.model.data.CareerModel
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class CareerActivity : BaseActivity() {
    private lateinit var binding: ActivityCareerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

        val careerList = arrayListOf<CareerModel>()
        careerList.add(CareerModel(R.drawable.law_illus, getString(R.string.law)))
        careerList.add(CareerModel(R.drawable.medical_illus, getString(R.string.medical)))
        careerList.add(CareerModel(R.drawable.sports_illus, getString(R.string.sports)))
        careerList.add(CareerModel(R.drawable.enterpuner_illus, getString(R.string.enterpreneur)))
        careerList.add(CareerModel(R.drawable.engineer_illus, getString(R.string.engineering)))

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter = CareerAdapter(this, careerList) {
            val careerInfoIntent = Intent(this@CareerActivity, CareerInfo::class.java)
            startActivity(careerInfoIntent)
        }

    }
}