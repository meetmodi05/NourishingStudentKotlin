package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.TopExpertAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.TopExpertUseCase
import com.example.nourishinggeniusstudent.model.ExportPost
import com.example.nourishinggeniusstudent.model.TopExpertModel

class TopExpertViewModel : BaseViewModel() {
    private var list: MutableList<ExportPost> = mutableListOf()
    private var liveData: MutableLiveData<List<TopExpertModel>> = MutableLiveData()
    private var adapter: TopExpertAdapter = TopExpertAdapter(list)

    private val topExpertUseCase by lazy { TopExpertUseCase(errorLiveData, liveData) }

    fun init() {
        adapter = TopExpertAdapter(list)
        liveData.observeForever {
            if (!it.isNullOrEmpty()) {
                list.clear()
//                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun getTopExpertList() = topExpertUseCase.getTopExportList()
    fun getAdapter(): TopExpertAdapter = adapter
}
