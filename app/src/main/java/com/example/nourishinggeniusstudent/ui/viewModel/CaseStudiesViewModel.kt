package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.CaseStudiesAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.CaseStudiesUseCase
import com.example.nourishinggeniusstudent.model.CaseStudiesModel
import com.example.nourishinggeniusstudent.model.CaseStudyPost

class CaseStudiesViewModel : BaseViewModel() {
    private val caseStudiesList: MutableList<CaseStudyPost> = mutableListOf()
    private val caseStudiesLiveData: MutableLiveData<List<CaseStudiesModel>> = MutableLiveData()
    private var caseStudiesAdapter: CaseStudiesAdapter = CaseStudiesAdapter(caseStudiesList)

    private val caseStudiesUseCase by lazy {
        CaseStudiesUseCase(errorLiveData, caseStudiesLiveData)
    }

    fun init() {
        caseStudiesAdapter = CaseStudiesAdapter(caseStudiesList)
        caseStudiesLiveData.observeForever {
            if (!it.isNullOrEmpty()) {
                caseStudiesList.clear()
//                caseStudiesList.addAll(it)
                caseStudiesAdapter.notifyDataSetChanged()

                println("+++++++++++caseStudiesList+++++++++++$caseStudiesList")
            } else {
                println("List has No Recored")
            }
        }
    }

    fun caseStudiesList() = caseStudiesUseCase.getCaseStudiesList()
    fun caseStudiesAdapter(): CaseStudiesAdapter = caseStudiesAdapter

}