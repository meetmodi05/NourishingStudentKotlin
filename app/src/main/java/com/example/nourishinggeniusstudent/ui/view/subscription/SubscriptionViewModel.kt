package com.example.nourishinggeniusstudent.ui.view.subscription

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.model.response.CaseStudyResponseModel
import com.example.nourishinggeniusstudent.networking.usecases.DomainUsecase
import com.example.nourishinggeniusstudent.networking.usecases.SubscriptionUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class SubscriptionViewModel(mContext: Context) : BaseViewModel(mContext) {

    val packages: MutableLiveData<PackagesModel> = MutableLiveData()

    val usecase = SubscriptionUsecase(
        mContext, errorLiveData, packages,
    )

    fun getSubscriptionPackages() {
        usecase.getSubscriptionPackages()
    }

}