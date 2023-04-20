package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.response.ForgotPasswordResponseModel
import com.example.nourishinggeniusstudent.model.response.VerifyOtpResponse
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import com.example.nourishinggeniusstudent.utils.MultipartUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class AuthUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var userData: MutableLiveData<User>? = null,
    private var updateUserData: MutableLiveData<User>? = null,
    private var feedback: MutableLiveData<String>? = null,
    private var forgotPwd: MutableLiveData<ForgotPasswordResponseModel>? = null,
    private var verifyOtpResponse: MutableLiveData<VerifyOtpResponse>? = null,
    private var resetPwd: MutableLiveData<BaseModel<Any?>>? = null,
    private var deleteAcc: MutableLiveData<BaseModel<Any?>>? = null,
) {

    fun registerUser(name: String, email: String, password: String, contact: String) {
        if (userData == null) return
        Networking.with(context).getServices().register(
            name = name.toRequestBody(),
            email = email.toRequestBody(),
            password = password.toRequestBody(),
            contactNumber = contact.toRequestBody(),
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    if (response.status == 202) {
                        errorLiveData.value = response.message
                    } else {
                        userData?.value = response.data
                    }
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun socialLogin(name: String, email: String, password: String, contact: String) {
        if (userData == null) return
        Networking.with(context).getServices().register(
            name = name.toRequestBody(),
            email = email.toRequestBody(),
            password = password.toRequestBody(),
            contactNumber = contact.toRequestBody(),
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun loginUser(email: String, password: String) {
        if (userData == null) return
        Networking.with(context).getServices().login(
            email = email.toRequestBody(),
            password = password.toRequestBody(),
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getuserinfobyid(id: String) {
        if (userData == null) return
        Networking.with(context).getServices().getuserinfobyid(
            id = id.toRequestBody(),
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun addUserFeedback(id: String, msg: String) {
        if (feedback == null) return
        Networking.with(context).getServices().addUserFeedback(
            id = id.toRequestBody(), msg.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<Any?>>() {
                override fun onSuccess(response: BaseModel<Any?>) {
                    feedback?.value = response.message
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }
            })
    }

    fun forgotPassword(email: String) {
        if (forgotPwd == null) return
        Networking.with(context).getServices().forgotpassword(
            email = email.toRequestBody(),
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<ForgotPasswordResponseModel>>() {
                override fun onSuccess(response: BaseModel<ForgotPasswordResponseModel>) {
                    forgotPwd?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun verifyOtp(email: String, code: String) {
        if (forgotPwd == null) return
        Networking.with(context).getServices().confirmcode(
            email = email.toRequestBody(), confirmationCode = code.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<VerifyOtpResponse>>() {
                override fun onSuccess(response: BaseModel<VerifyOtpResponse>) {
                    verifyOtpResponse?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun resetPwd(userId: String, password: String) {
        if (resetPwd == null) return
        Networking.with(context).getServices().resetPassword(
            userId.toRequestBody(), password.toRequestBody(), password.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<Any?>>() {
                override fun onSuccess(response: BaseModel<Any?>) {
                    resetPwd?.value = response
                }

                override fun onFailed(code: Int, message: String) {
                    val model = BaseModel<Any?>()
                    model.message = message
                    resetPwd?.value = model
                }

            })
    }

    fun deleteAccount(userId: String) {
        if (deleteAcc == null) return
        Networking.with(context).getServices().deleteAccount(
            userId.toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<Any?>>() {
                override fun onSuccess(response: BaseModel<Any?>) {
                    deleteAcc?.value = response
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun editProfile(
        userId: String,
        name: String,
        contact: String,
        address: String,
        dob: String,
        profilePic: Uri?
    ) {
        if (deleteAcc == null) return

        val map: HashMap<String, RequestBody> = hashMapOf()
        map["user_id"] = userId.toRequestBody()
        map["first_name"] = name.toRequestBody()
        map["contact_number"] = contact.toRequestBody()
        map["user_address"] = address.toRequestBody()
        map["date_of_birth"] = dob.toRequestBody()
        var pic = if (profilePic != null) {
            MultipartUtils.prepareFilePart(mContext = context, profilePic)
        } else {
            null
        }
        Networking.with(context).getServices().editProfile(
            map, pic
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<User>>() {
                override fun onSuccess(response: BaseModel<User>) {
                    userData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }
}