package com.example.nourishinggeniusstudent.ui.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.response.DashboardResponseModel
import com.example.nourishinggeniusstudent.networking.usecases.DashboardUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class DashboardViewModel(mContext: Context) : BaseViewModel(mContext) {

    val dashboardData: MutableLiveData<DashboardResponseModel> = MutableLiveData()
    val subscribeToNewsletter: MutableLiveData<String> = MutableLiveData()

    val usecase = DashboardUsecase(
        mContext, errorLiveData, dashboardData
    )

    fun getDashboardData() {
        usecase.getDashboardData()
    }

    fun subscribeToNewsletter(email: String) {
        usecase.subscribeToNewsletter(email)
    }

}