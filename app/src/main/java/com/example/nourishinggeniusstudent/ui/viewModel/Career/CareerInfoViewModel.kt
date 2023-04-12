package com.example.nourishinggeniusstudent.ui.viewModel.Career

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.CareerInfoDetail
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class CareerInfoViewModel: BaseViewModel() {
    private var careerInfoList:MutableList<CareerInfoDetail> = mutableListOf()
    private var careerInfoLiveData :MutableLiveData<CareerInfoDetail> = MutableLiveData()

}