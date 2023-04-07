package com.example.nourishinggeniusstudent.Networking.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.SignUpRepo
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.model.BaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignUpUseCase(
    private val errorLiveData: MutableLiveData<String?>,
    private val signUpLiveData: MutableLiveData<SignUpModel>? = null
) {
    private val signUpRepo = SignUpRepo()
    fun createUser() {
        if (signUpLiveData == null) return

        signUpRepo.signIn().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<SignUpModel>>() {
                override fun onSuccess(response: BaseModel<SignUpModel>) {
                    Log.e("TAG", "onSuccess: " + response.message + response.statusCode)

                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }

            })
    }
}