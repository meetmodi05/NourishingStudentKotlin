package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.CaseStudiesAdapter
import com.example.nourishinggeniusstudent.Adapter.DashboardDataPostAdapter
import com.example.nourishinggeniusstudent.Adapter.TopExpertAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.DashboardUseCase
import com.example.nourishinggeniusstudent.model.CaseStudyPost
import com.example.nourishinggeniusstudent.model.DefaultDataPost
import com.example.nourishinggeniusstudent.model.ExportPost

class DashboardViewModel : BaseViewModel() {
    //    list
    private var list: MutableList<DefaultDataPost> = mutableListOf()
    private var exportList: MutableList<ExportPost> = mutableListOf()
    private var caseStudyList: MutableList<CaseStudyPost> = mutableListOf()

    //    liveData
    private var liveData: MutableLiveData<List<DefaultDataPost>?> = MutableLiveData()
    private var exportLiveData: MutableLiveData<List<ExportPost>?> = MutableLiveData()
    private var caseStudyLiveData: MutableLiveData<List<CaseStudyPost>?> = MutableLiveData()

    //    Adapter
    private var adapter: DashboardDataPostAdapter = DashboardDataPostAdapter(list)
    private var exportAdapter: TopExpertAdapter = TopExpertAdapter(exportList)
    private var caseStudyAdapter: CaseStudiesAdapter = CaseStudiesAdapter(caseStudyList)

    private val dashboardUseCase by lazy {
        DashboardUseCase(errorLiveData, liveData, exportLiveData, caseStudyLiveData)
    }

    fun init() {
        liveData.observeForever {
            list.clear()
            if (it != null) {
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
        exportLiveData.observeForever {
            exportList.clear()
            if (it != null) {
                exportList.addAll(it)
                exportAdapter.notifyDataSetChanged()

            }
        }
        caseStudyLiveData.observeForever {
            caseStudyList.clear()
            caseStudyList.addAll(it!!)
            caseStudyAdapter.notifyDataSetChanged()
        }
    }

    fun getList() = dashboardUseCase.getDashboardList()
    fun getDashboardTopExportList() = dashboardUseCase.getDashboardDataDomain()
    fun getDashboardCaseStudy() = dashboardUseCase.getDashboardCaseStudy()
    fun getAdapter(): DashboardDataPostAdapter = adapter
    fun getExportDashboardAdapter(): TopExpertAdapter = exportAdapter
    fun getCaseStudyDashboardAdapter(): CaseStudiesAdapter = caseStudyAdapter

}