package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.CaseStudiesRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.CaseStudiesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CaseStudiesUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var caseStudiesLiveData: MutableLiveData<List<CaseStudiesModel>>
) {
    private val repo by lazy { CaseStudiesRepo() }

    fun getCaseStudiesList() {
        repo.caseStudies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<CaseStudiesModel>>>() {
                override fun onSuccess(response: BaseModel<List<CaseStudiesModel>>) {
                    caseStudiesLiveData.postValue(response.dataModel)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}