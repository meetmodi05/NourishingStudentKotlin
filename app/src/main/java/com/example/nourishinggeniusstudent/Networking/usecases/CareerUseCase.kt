package com.example.nourishinggeniusstudent.Networking.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.Networking.repo.CareerRepo
import com.example.nourishinggeniusstudent.model.BaseModel
import com.example.nourishinggeniusstudent.model.CareerPost
import com.example.nourishinggeniusstudent.model.DataModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CareerUseCase(
    private val errorLiveData: MutableLiveData<String?>,
    private val careerLiveData: MutableLiveData<List<CareerPost>>? = null,
) {
    private val careerRepo by lazy { CareerRepo() }

    fun getCareerList() {
        if (careerLiveData == null) return

        careerRepo.getCareer().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<DataModel>>() {
                override fun onSuccess(response: BaseModel<DataModel>) {
                    Log.d("Message", response.message + response.statusCode)
                    careerLiveData.postValue(response.dataModel?.careersPosts)
                }

                override fun onFailed(code: Int, message: String?) {
                    Log.e("Error", "$message++++code+$code+++++++++++================")
                    errorLiveData.postValue(message)
                }

            })
    }

}



