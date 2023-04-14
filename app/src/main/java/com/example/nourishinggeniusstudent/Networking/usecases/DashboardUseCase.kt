package com.example.nourishinggeniusstudent.Networking.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.DashboardRepo
import com.example.nourishinggeniusstudent.model.CaseStudyPost
import com.example.nourishinggeniusstudent.model.DashboardModel
import com.example.nourishinggeniusstudent.model.DataCareers
import com.example.nourishinggeniusstudent.model.DataDomainExport
import com.example.nourishinggeniusstudent.model.DataPost
import com.example.nourishinggeniusstudent.model.DefaultDataPost
import com.example.nourishinggeniusstudent.model.ExportPost
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashboardUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var liveData: MutableLiveData<List<DefaultDataPost>?>,
    private var exportLiveData: MutableLiveData<List<ExportPost>?>,
    private var caseStudyLiveData: MutableLiveData<List<CaseStudyPost>?>,
) {
    private val repo by lazy { DashboardRepo() }

    fun getDashboardList() {
        repo.getDashboardList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<DashboardModel<DataPost>>() {
                override fun onSuccess(response: DashboardModel<DataPost>) {
                    Log.d("Success", "onSuccess: $response")
                    liveData.postValue(response.dataPost?.defaultDataPosts)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                    Log.e("TAG", "onFailed: $code=====>$message")
                }
            })
    }

    fun getDashboardDataDomain() {
        repo.getDashboardDomain().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<DashboardModel<DataDomainExport>>() {
                override fun onSuccess(response: DashboardModel<DataDomainExport>) {
                    Log.d("Success", "onSuccess: $response")
                    exportLiveData.postValue(response.dataDomainExport?.post)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                    Log.e("TAG", "onFailed: $code=====>$message")
                }
            })
    }

    fun getDashboardCaseStudy() {
        repo.getDashboardCaseStudy().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<DashboardModel<DataCareers>>() {
                override fun onSuccess(response: DashboardModel<DataCareers>) {
                    Log.d("Success", "onSuccess: $response")
                    caseStudyLiveData.postValue(response.dataCareers?.caseStudyPosts)
                }

                override fun onFailed(code: Int, message: String?) {
                    Log.e("onFailed", "onFailed: $code with $message")
                    errorLiveData.postValue(message)
                }

            })
    }
}