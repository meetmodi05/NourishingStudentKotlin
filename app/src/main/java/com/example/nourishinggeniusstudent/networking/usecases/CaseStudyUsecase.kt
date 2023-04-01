package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.response.CaseStudyResponseModel
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class CaseStudyUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var caseStudyRoles: MutableLiveData<List<SortRoles>>? = null,
    private var caseStudyList: MutableLiveData<CaseStudyResponseModel>? = null,
    private var caseStudyData: MutableLiveData<CaseStudyData>? = null,
) {

    fun getCaseStudyRoles() {
        if (caseStudyRoles == null) return
        Networking.with(context).getServices().getCaseStudyRoles(
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<SortRoles>>>() {
                override fun onSuccess(response: BaseModel<List<SortRoles>>) {
                    caseStudyRoles?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getCaseStudyData(id: Int?) {
        if (caseStudyRoles == null) return
        Networking.with(context).getServices().getCaseStudyData(
            id = id.toString().toRequestBody(), role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<CaseStudyData>>() {
                override fun onSuccess(response: BaseModel<CaseStudyData>) {
                    caseStudyData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getCaseStudyList(taxId: Int? = null, search: String = "") {
        if (caseStudyRoles == null) return
        val request = HashMap<String, RequestBody>()
        request["role"] = "student".toRequestBody()
        if (taxId != null) {
            request["tax_id"] = taxId.toString().toRequestBody()
        }
        if (search.isNotBlank()) {
            request["search_term"] = search.toRequestBody()
        }

        Networking.with(context).getServices().getCaseStudyList(
            request
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<CaseStudyResponseModel>>() {
                override fun onSuccess(response: BaseModel<CaseStudyResponseModel>) {
                    caseStudyList?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}