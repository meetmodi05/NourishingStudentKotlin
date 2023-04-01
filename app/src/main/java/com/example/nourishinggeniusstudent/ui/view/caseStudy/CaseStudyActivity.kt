package com.example.nourishinggeniusstudent.ui.view.caseStudy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityCaseStudyBinding
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.ui.adapter.CaseStudyAdapter
import com.example.nourishinggeniusstudent.ui.adapter.SortAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.Constants

class CaseStudyActivity : BaseActivity() {
    private var adapter: CaseStudyAdapter? = null
    private lateinit var binding: ActivityCaseStudyBinding
    private val viewModel by lazy { CaseStudyViewModel(this) }
    private var sortId: Int? = null

    private val caseStudyList: ArrayList<CaseStudyData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaseStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }
        viewModel.isLoading.value = true
        initView()
        setObservers()
    }

    private fun initView() {

        binding.rvBLogList.layoutManager = GridLayoutManager(this, 2)
        adapter = CaseStudyAdapter {
            val intent = Intent(this@CaseStudyActivity, CaseStudyDetailsActivity::class.java)
            intent.putExtra(Constants.CASE_STUDY_ID, it.id)
            startActivity(intent)
        }
        binding.rvBLogList.adapter = adapter

        binding.etSearch.addTextChangedListener {
            Handler(Looper.getMainLooper()).postDelayed({
                getCaseStudyList()
            }, 1000)
        }
        viewModel.getCaseStudyRoles()
        getCaseStudyList()
    }

    private fun setObservers() {
        viewModel.caseStudyRoles.observe(this) { listData ->
            setSortDropdown(listData)
        }
        viewModel.caseStudyList.observe(this) {
            viewModel.isLoading.value = false
            caseStudyList.clear()
            caseStudyList.addAll(it.list)
            adapter?.setList(caseStudyList)
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
            getCaseStudyList()
        }
    }

    private fun getCaseStudyList() {
        viewModel.getCaseStudyList(sortId, binding.etSearch.text.toString())
    }
}