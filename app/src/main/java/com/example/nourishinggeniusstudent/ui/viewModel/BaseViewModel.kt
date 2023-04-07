package com.example.nourishinggeniusstudent.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    val errorLiveData: MutableLiveData<String?> = MutableLiveData()

    init {
        //TO display error/failed api message
        errorLiveData.observeForever {
            isLoading.value = false
            isRefreshing.value = false
        }

        /*isLoading.observe(mContext as BaseActivity, {
            if (it) mContext.showProgressbar()
            else mContext.hideProgressbar()
        })*/
    }

}