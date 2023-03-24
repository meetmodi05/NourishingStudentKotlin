package com.example.nourishinggeniusstudent.networking.network

import android.os.StrictMode
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.utils.Constants
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.InetAddress

abstract class CallbackObserver<T> : DisposableObserver<T>() {

    abstract fun onSuccess(response: T)
    abstract fun onFailed(code: Int, message: String)

    override fun onComplete() {
        //Nothing happen here
    }

    override fun onNext(t: T) {
        val obj = t as BaseModel<Any?>
        if (t.status == 200) {
            onSuccess(t)
        } else {
            onFailed(0, t.message)
        }
    }

    override fun onError(e: Throwable) {
        if (!isInternetAvailable()) {
            onFailed(0, "No Internet connection")
        } else if (e is HttpException) {
            onFailed(e.code(), getErrorMessage(e.response()?.errorBody()))
        } else if (e is IllegalStateException) {
            onFailed(0, Constants.ILLEGAL_STATE_EXCEPTION)
        } else {
            onFailed(0, e.localizedMessage)
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message.toString()
        }

    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val ipAddr = InetAddress.getByName("google.com")
            //You can replace it with your name
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }
}