package com.example.nourishinggeniusstudent.Networking.usecases

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.PackagesRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.Packages.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PackagesUseCase(
    private val errorLiveData: MutableLiveData<String?>,
    private val packagesLiveData: MutableLiveData<List<PackagesModel>>? = null,
) {
    private val packagesRepo by lazy { PackagesRepo() }

    fun packagesList() {
        if (packagesLiveData == null) return
        packagesRepo.packagesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<PackageDataModel>>() {
                override fun onSuccess(response: BaseModel<PackageDataModel>) {
                    packagesLiveData.postValue(response.dataModel?.packagesPosts)
                }

                override fun onFailed(code: Int, message: String?) {
                    errorLiveData.postValue(message)
                }
            })
    }
}
