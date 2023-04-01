package com.example.nourishinggeniusstudent.ui.view.domain

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.model.response.CaseStudyResponseModel
import com.example.nourishinggeniusstudent.networking.usecases.DomainUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class DomainViewModel(mContext: Context) : BaseViewModel(mContext) {

    val domainRoles: MutableLiveData<List<SortRoles>> = MutableLiveData()
    val domainList: MutableLiveData<List<DomainData>> = MutableLiveData()
    val domainData: MutableLiveData<DomainData> = MutableLiveData()

    val usecase = DomainUsecase(
        mContext, errorLiveData, domainRoles, domainList, domainData
    )

    fun getDomainRoles() {
        usecase.getDomainRoles()
    }

    fun getDomainList(taxId: Int? = null, search: String = "") {
        usecase.getDomainList(taxId, search)
    }

    fun getDomainData(id: Int? = null) {
        usecase.getDomainData(id)
    }

}