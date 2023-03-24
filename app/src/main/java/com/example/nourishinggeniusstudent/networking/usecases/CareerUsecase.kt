package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.careers.CareerDetails
import com.example.nourishinggeniusstudent.model.response.CareersListResponseModel
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody.Companion.toRequestBody

class CareerUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var careersList: MutableLiveData<CareersListResponseModel>? = null,
    private var careerDetails: MutableLiveData<CareerDetails>? = null,
) {

    fun getCareers() {
        if (careersList == null) return
        Networking.with(context).getServices().getCareers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<CareersListResponseModel>>() {
                override fun onSuccess(response: BaseModel<CareersListResponseModel>) {
                    careersList?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getCareerDetails(careerId: Int) {
        if (careerDetails == null) return
        Networking.with(context).getServices()
            .getCareerDetails(careerId = careerId.toString().toRequestBody())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<CareerDetails>>() {
                override fun onSuccess(response: BaseModel<CareerDetails>) {
                    careerDetails?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }
            })
    }
}