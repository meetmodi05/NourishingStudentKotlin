package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.DomainRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.Domain.DomainModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DomainUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var domainLiveData: MutableLiveData<List<DomainModel>>? = null,
) {
    private val domainRepo by lazy { DomainRepo() }
    fun domainList() {
        domainRepo.getDomain().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<DomainModel>>>() {
                override fun onSuccess(response: BaseModel<List<DomainModel>>) {
                    domainLiveData?.postValue(response.dataModel)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}