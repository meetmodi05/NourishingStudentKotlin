package com.example.nourishinggeniusstudent.ui.view.Career

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.Adapter.CareerAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityCareerBinding
import com.example.nourishinggeniusstudent.model.CareerModel

class CareerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

        val careerList= arrayListOf<CareerModel>()
        careerList.add(CareerModel(R.drawable.law_illus, getString(R.string.law)))
        careerList.add(CareerModel(R.drawable.medical_illus, getString(R.string.medical)))
        careerList.add(CareerModel(R.drawable.sports_illus, getString(R.string.sports)))
        careerList.add(CareerModel(R.drawable.enterpuner_illus, getString(R.string.enterpreneur)))
        careerList.add(CareerModel(R.drawable.engineer_illus, getString(R.string.engineering)))

        binding.recyclerView.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter= CareerAdapter(this,careerList)

    }
}