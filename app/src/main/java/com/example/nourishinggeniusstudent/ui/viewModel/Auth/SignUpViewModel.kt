package com.example.nourishinggeniusstudent.ui.viewModel.Auth

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Networking.usecases.SignUpUseCase
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class SignUpViewModel() : BaseViewModel() {
    private var signUpList: MutableList<SignUpModel> = mutableListOf()
    private var signUpLiveData = MutableLiveData<SignUpModel>()

    private val signUpUseCase by lazy {
        SignUpUseCase(
            errorLiveData = errorLiveData,
            signUpLiveData = signUpLiveData
        )
    }
    fun init() {
        signUpUseCase.createUser()
    }

}