package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class SubscriptionUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var subscriptionPackages: MutableLiveData<PackagesModel>? = null,
) {

    fun getSubscriptionPackages() {
        if (subscriptionPackages == null) return
        Networking.with(context).getServices().getSubscriptionPackages(
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<PackagesModel>>() {
                override fun onSuccess(response: BaseModel<PackagesModel>) {
                    subscriptionPackages?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}