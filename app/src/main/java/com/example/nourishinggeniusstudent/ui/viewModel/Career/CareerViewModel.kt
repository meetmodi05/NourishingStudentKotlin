package com.example.nourishinggeniusstudent.ui.viewModel.Career

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.CareerAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.CareerUseCase
import com.example.nourishinggeniusstudent.model.CareerPost
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class CareerViewModel : BaseViewModel() {
    private var careerList: MutableList<CareerPost> = mutableListOf()
    private var careerLiveData: MutableLiveData<List<CareerPost>> = MutableLiveData()
    private var careerAdapter: CareerAdapter = CareerAdapter(careerList)

    private val careerUseCase by lazy {
        CareerUseCase(errorLiveData = errorLiveData, careerLiveData = careerLiveData)
    }

    fun init() {
        careerAdapter = CareerAdapter(careerList)
        careerLiveData.observeForever {
            if (!it.isNullOrEmpty()) {
                careerList.clear()
                careerList.addAll(it)
                careerAdapter.notifyDataSetChanged()

                println("++++++++++careerList++++++++++$careerList")
            } else {
                println("List Has No Record")
            }
        }
    }

    fun getCareerList() = careerUseCase.getCareerList()
    fun getCareerAdapter(): CareerAdapter = careerAdapter
}
