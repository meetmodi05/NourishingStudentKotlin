package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.model.data.PaymentHistory
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
    private var responseCreatePayment: MutableLiveData<BaseModel<Any?>>? = null,
    private var responsePaymentHistory: MutableLiveData<List<PaymentHistory>>? = null,
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

    fun createPayment(packageId: String, userId: String, payId: String) {
        if (responseCreatePayment == null) return
        Networking.with(context).getServices().createPayment(
            packageId.toRequestBody(), userId.toRequestBody(), payId.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<Any?>>() {
                override fun onSuccess(response: BaseModel<Any?>) {
                    responseCreatePayment?.value = response
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun paymentHistory(userId: String) {
        if (responsePaymentHistory == null) return
        Networking.with(context).getServices().paymentHistory(
            userId.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<PaymentHistory>>>() {
                override fun onSuccess(response: BaseModel<List<PaymentHistory>>) {
                    responsePaymentHistory?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}