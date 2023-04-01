package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.response.ForgotPasswordResponseModel
import com.example.nourishinggeniusstudent.model.response.VerifyOtpResponse
import com.example.nourishinggeniusstudent.networking.usecases.AuthUsecase
import com.example.nourishinggeniusstudent.ui.view.base.BaseViewModel
import okhttp3.RequestBody

class AuthViewModel(mContext: Context) : BaseViewModel(mContext) {

    val userData: MutableLiveData<User> = MutableLiveData()
    val forgotPwd: MutableLiveData<ForgotPasswordResponseModel> = MutableLiveData()
    val otpResponse: MutableLiveData<VerifyOtpResponse> = MutableLiveData()
    val resetPwd: MutableLiveData<BaseModel<Any?>> = MutableLiveData()
    val deleteAccResponse: MutableLiveData<BaseModel<Any?>> = MutableLiveData()
    val feedback: MutableLiveData<String> = MutableLiveData()

    val usecase = AuthUsecase(
        mContext,
        errorLiveData,
        userData,
        feedback,
        forgotPwd,
        otpResponse,
        resetPwd,
        deleteAccResponse
    )

    fun registerUser(name: String, email: String, password: String, contact: String) {
        usecase.registerUser(name, email, password, contact)
    }
    fun socialLogin(name: String, email: String, password: String, contact: String) {
        usecase.socialLogin(name, email, password, contact)
    }

    fun loginUser(email: String, password: String) {
        usecase.loginUser(email, password)
    }

    fun forgotPwd(email: String) {
        usecase.forgotPassword(email)
    }

    fun verifyOtp(email: String, code: String) {
        usecase.verifyOtp(email, code)
    }

    fun resetPassword(userId: String, password: String) {
        usecase.resetPwd(userId, password)
    }

    fun deleteAccount(userId: String) {
        usecase.deleteAccount(userId)
    }

    fun getuserinfobyid(userId: String) {
        usecase.getuserinfobyid(userId)
    }

    fun addUserFeedback(userId: String, msg: String) {
        usecase.addUserFeedback(userId, msg)
    }

    fun editProfile(
        userId: String,
        name: String,
        contact: String,
        address: String,
        dob: String,
        profilePic: Uri? = null
    ) {
        usecase.editProfile(userId, name, contact, address, dob, profilePic)
    }

}