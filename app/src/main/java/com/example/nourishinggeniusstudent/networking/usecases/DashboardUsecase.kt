package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.response.CaseStudyResponseModel
import com.example.nourishinggeniusstudent.model.response.DashboardResponseModel
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class DashboardUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var dashboardData: MutableLiveData<DashboardResponseModel>? = null,
    private var subscribe: MutableLiveData<String>? = null,
) {

    fun getDashboardData() {
        if (dashboardData == null) return
        Networking.with(context).getServices().getDashboardData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<DashboardResponseModel>() {

                override fun onNext(response: DashboardResponseModel) {
                    if (response.status == 200) {
                        dashboardData?.value = response
                    } else {
                        errorLiveData.value = response.message
                    }
                }

                override fun onError(e: Throwable) {
                    errorLiveData.value = e.localizedMessage
                }

                override fun onComplete() {
                }

            })
    }

    fun subscribeToNewsletter(email:String) {
        if (subscribe == null) return
        Networking.with(context).getServices().subscribeToNewsletter(email.toRequestBody()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<Any?>>() {
                override fun onSuccess(response: BaseModel<Any?>) {
                    subscribe?.value = response.message
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}