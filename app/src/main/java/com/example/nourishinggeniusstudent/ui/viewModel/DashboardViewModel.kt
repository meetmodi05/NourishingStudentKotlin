package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.DashboardModel

class DashboardViewModel {
    private var list: MutableList<DashboardModel> = mutableListOf()
    private var liveData: MutableLiveData<DashboardModel> = MutableLiveData()
}