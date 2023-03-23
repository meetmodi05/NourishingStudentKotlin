package com.example.nourishinggeniusstudent.ui.view.base

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(private val mContext: Context) : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        errorLiveData.observeForever {
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
            isLoading.value = false
            isRefreshing.value = false
        }

        isLoading.observe(mContext as BaseActivity) {
            if (it) mContext.showProgressbar()
            else mContext.hideProgressbar()
        }
    }

}
