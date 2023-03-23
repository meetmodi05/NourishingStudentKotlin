package com.example.nourishinggeniusstudent.ui.view.career

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDegreeListBinding
import com.example.nourishinggeniusstudent.model.data.CareerModel
import com.example.nourishinggeniusstudent.ui.adapter.CareerAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class DegreeList : BaseActivity() {
    private lateinit var binding: ActivityDegreeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDegreeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

        val careerList = arrayListOf<CareerModel>()
        careerList.add(CareerModel(R.drawable.ic_science, getString(R.string.science)))
        careerList.add(CareerModel(R.drawable.ic_commerce, getString(R.string.commerce)))
        careerList.add(CareerModel(R.drawable.ic_arts, getString(R.string.arts)))

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter = CareerAdapter(this@DegreeList, careerList) {
            val careerInfoIntent = Intent(this@DegreeList, CareerActivity::class.java)
            startActivity(careerInfoIntent)
        }

    }
}