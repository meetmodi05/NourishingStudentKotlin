package com.example.nourishinggeniusstudent.ui.view.career

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDegreeListBinding
import com.example.nourishinggeniusstudent.model.careers.Careers
import com.example.nourishinggeniusstudent.model.careers.EducationDegree
import com.example.nourishinggeniusstudent.ui.adapter.CareerAdapter
import com.example.nourishinggeniusstudent.ui.adapter.DegreeAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class DegreeList : BaseActivity() {
    private lateinit var binding: ActivityDegreeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDegreeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.close.setOnClickListener { finish() }

        val list = arrayListOf<EducationDegree>()
        list.add(EducationDegree(0, getString(R.string.science), R.drawable.ic_science))
        list.add(EducationDegree(0, getString(R.string.commerce), R.drawable.ic_commerce))
        list.add(EducationDegree(0, getString(R.string.arts), R.drawable.ic_arts))

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter = DegreeAdapter(list) {
            val careerInfoIntent = Intent(this@DegreeList, CareerActivity::class.java)
            startActivity(careerInfoIntent)
        }

    }
}