package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.TopExpertRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.TopExpertModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopExpertUseCase(
    private var errorLiveData: MutableLiveData<String?>,
    private var liveData: MutableLiveData<List<TopExpertModel>>,
) {
    private val repo by lazy { TopExpertRepo() }

    fun getTopExportList() {
        repo.topExpertList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<TopExpertModel>>>() {
                override fun onSuccess(response: BaseModel<List<TopExpertModel>>) {
                    liveData.postValue(response.dataModel)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}