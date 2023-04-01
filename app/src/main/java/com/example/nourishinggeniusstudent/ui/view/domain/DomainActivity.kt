package com.example.nourishinggeniusstudent.ui.view.domain

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityDomainBinding
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.ui.adapter.CaseStudyAdapter
import com.example.nourishinggeniusstudent.ui.adapter.DomainAdapter
import com.example.nourishinggeniusstudent.ui.adapter.SortAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyDetailsActivity
import com.example.nourishinggeniusstudent.ui.view.caseStudy.CaseStudyViewModel
import com.example.nourishinggeniusstudent.ui.view.caseStudy.DomainDetailsActivity
import com.example.nourishinggeniusstudent.utils.Constants

class DomainActivity : BaseActivity() {
    private var adapter: DomainAdapter? = null
    private lateinit var binding: ActivityDomainBinding
    private val viewModel by lazy { DomainViewModel(this) }
    private var sortId: Int? = null

    private val domainList: ArrayList<DomainData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }
        viewModel.isLoading.value = true
        initView()
        setObservers()
    }

    private fun initView() {

        binding.rvBLogList.layoutManager = GridLayoutManager(this, 2)
        adapter = DomainAdapter {
            val intent = Intent(this@DomainActivity, DomainDetailsActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        binding.rvBLogList.adapter = adapter

        binding.etSearch.addTextChangedListener {
            Handler(Looper.getMainLooper()).postDelayed({
                getDomainList()
            }, 1000)
        }
        viewModel.getDomainRoles()
        getDomainList()
    }

    private fun setObservers() {
        viewModel.domainRoles.observe(this) { listData ->
            setSortDropdown(listData)
        }
        viewModel.domainList.observe(this) {
            viewModel.isLoading.value = false
            domainList.clear()
            domainList.addAll(it)
            adapter?.setList(domainList)
        }
    }

    private fun setSortDropdown(dropList: List<SortRoles>) {
        val aa = SortAdapter(this, dropList)
        binding.autoCompleteTV.setAdapter(aa)
        binding.autoCompleteTV.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.isLoading.value = true
            val item = aa.getItem(i)
            sortId = item.id
            binding.autoCompleteTV.setText(item.termName)
            getDomainList()
        }
    }

    private fun getDomainList() {
        viewModel.getDomainList(sortId, binding.etSearch.text.toString())
    }
}