package com.example.nourishinggeniusstudent.ui.viewModel.Career

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.CareerInfoAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.CareerInfoUseCase
import com.example.nourishinggeniusstudent.databinding.CareerinfolayoutBinding
import com.example.nourishinggeniusstudent.model.CareerInfoDetail
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class CareerInfoViewModel : BaseViewModel() {
    private var careerInfoList: MutableList<CareerInfoDetail> = mutableListOf()
    private var careerInfoLiveData: MutableLiveData<CareerInfoDetail> = MutableLiveData()
//    private var careerInfoAdapter: CareerInfoAdapter = CareerInfoAdapter(careerInfoList)

    private val useCase by lazy { CareerInfoUseCase(errorLiveData, careerInfoLiveData) }
    fun init() {
        println("++++++++$careerInfoList++++++++++")

    }

    fun getCareerInfoList() = useCase.careerInfoList()
}