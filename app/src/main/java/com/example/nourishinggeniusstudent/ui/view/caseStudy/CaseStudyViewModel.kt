package com.example.nourishinggeniusstudent.ui.view.caseStudy

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.response.CaseStudyResponseModel
import com.example.nourishinggeniusstudent.networking.usecases.CaseStudyUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class CaseStudyViewModel(mContext: Context) : BaseViewModel(mContext) {

    val caseStudyRoles: MutableLiveData<List<SortRoles>> = MutableLiveData()
    val caseStudyList: MutableLiveData<CaseStudyResponseModel> = MutableLiveData()
    val caseStudyData: MutableLiveData<CaseStudyData> = MutableLiveData()

    val usecase = CaseStudyUsecase(
        mContext, errorLiveData, caseStudyRoles, caseStudyList,caseStudyData
    )

    fun getCaseStudyRoles() {
        usecase.getCaseStudyRoles()
    }

    fun getCaseStudyList(taxId: Int? = null, search: String = "") {
        usecase.getCaseStudyList(taxId, search)
    }

    fun getCaseStudyData(id: Int? = null) {
        usecase.getCaseStudyData(id)
    }

}