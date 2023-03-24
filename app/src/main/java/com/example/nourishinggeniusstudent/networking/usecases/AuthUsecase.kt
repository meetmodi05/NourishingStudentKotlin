package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody.Companion.toRequestBody

class AuthUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var userData: MutableLiveData<User>? = null,
) {

    fun registerUser(email: String, password: String) {
        if (userData == null) return
        Networking.with(context).getServices().register(
            email = email.toRequestBody(),
            password = password.toRequestBody(),
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }
    fun loginUser(email: String, password: String) {
        if (userData == null) return
        Networking.with(context).getServices().login(
            email = email.toRequestBody(),
            password = password.toRequestBody(),
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }
}