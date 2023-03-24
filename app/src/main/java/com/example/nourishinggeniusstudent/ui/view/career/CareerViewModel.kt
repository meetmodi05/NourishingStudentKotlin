package com.example.nourishinggeniusstudent.ui.view.career

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.careers.CareerDetails
import com.example.nourishinggeniusstudent.model.response.CareersListResponseModel
import com.example.nourishinggeniusstudent.networking.usecases.CareerUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class CareerViewModel(mContext: Context) : BaseViewModel(mContext) {

    var careersList: MutableLiveData<CareersListResponseModel> = MutableLiveData()
    var careerDetails: MutableLiveData<CareerDetails> = MutableLiveData()
    private var usecase = CareerUsecase(mContext, errorLiveData, careersList, careerDetails)

    fun getCareers() = usecase.getCareers()
    fun getCareerDetails(careerId: Int) = usecase.getCareerDetails(careerId)

}