package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.CareerInfoRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.CareerInfoDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CareerInfoUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var careerInfoLiveData: MutableLiveData<CareerInfoDetail>? = null
) {
    private val careerInfoRepo by lazy { CareerInfoRepo() }
    fun careerInfoList() {
        careerInfoRepo.careerInfo().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<CareerInfoDetail>>() {
                override fun onSuccess(response: BaseModel<CareerInfoDetail>) {
                    careerInfoLiveData?.postValue(response.dataModel)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}