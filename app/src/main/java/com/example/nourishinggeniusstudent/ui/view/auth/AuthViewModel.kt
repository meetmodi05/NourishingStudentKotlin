package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.networking.usecases.AuthUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel

class AuthViewModel(mContext: Context) : BaseViewModel(mContext) {

    val userData: MutableLiveData<User> = MutableLiveData()

    val usecase = AuthUsecase(mContext, errorLiveData, userData)

    fun registerUser(name: String, email: String, password: String, contact: String) =
        usecase.registerUser(name, email, password, contact)

    fun loginUser(email: String, password: String) = usecase.loginUser(email, password)

}