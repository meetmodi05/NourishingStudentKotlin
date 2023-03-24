package com.example.nourishinggeniusstudent.ui.view.career

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nourishinggeniusstudent.ui.adapter.CareerAdapter
import com.example.nourishinggeniusstudent.databinding.ActivityCareerBinding
import com.example.nourishinggeniusstudent.model.careers.Careers
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.Constants

class CareerActivity : BaseActivity() {
    private var adapter: CareerAdapter ?= null
    private lateinit var binding: ActivityCareerBinding
    private val viewModel by lazy { CareerViewModel(this@CareerActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setClickListeners()
        setObservers()
        viewModel.getCareers()
    }

    private fun initView() {
        binding.etSearch.doAfterTextChanged {
            adapter?.filter?.filter(it)
        }
    }

    private fun setObservers() {
        viewModel.careersList.observe(this) {
            viewModel.isLoading.value = false
            setUpAdapter(it.careers_posts)
            adapter?.filter?.filter("")
        }
    }

    private fun setClickListeners() {
        binding.close.setOnClickListener { finish() }
    }

    fun setUpAdapter(careerList: ArrayList<Careers>) {
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = CareerAdapter(careerList) {
            val intent = Intent(this@CareerActivity, CareerInfo::class.java)
            intent.putExtra(Constants.CAREER_ID, it.career_id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }

}