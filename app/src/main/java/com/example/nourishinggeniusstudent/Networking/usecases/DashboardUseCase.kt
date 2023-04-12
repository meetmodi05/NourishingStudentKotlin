package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.DashboardRepo
import com.example.nourishinggeniusstudent.model.DashboardModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashboardUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var liveData: MutableLiveData<DashboardModel>
) {
    private val repo by lazy { DashboardRepo() }

    fun getDashboardList() {
        repo.getDashboardList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<DashboardModel>() {
                override fun onSuccess(response: DashboardModel) {
                    liveData.postValue(response)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}