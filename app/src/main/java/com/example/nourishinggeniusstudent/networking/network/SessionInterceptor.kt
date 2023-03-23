package com.example.nourishinggeniusstudent.networking.network

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.example.nourishinggeniusstudent.utils.Session
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SessionInterceptor(val context: Context, var token: String?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val session = Session(context)

        val builder = original.newBuilder()
        builder.header("Accept", "application/json")
        if (session.isLoggedIn) {
            builder.header("Authorization", "Bearer $token")
        }
        builder.method(original.method, original.body)
        val request = builder.build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            Log.e("Session Expired : Endpoint", response.request.url.encodedPath)

            val intent = Intent(context, LoginActivity::class.java)
            try {
                val jsonObject = JSONObject(response.body?.string())
                val msg = jsonObject.optString("Message")
                intent.putExtra(Constants.SESSION_EXPIRE_MSG, msg)
            } catch (e: Exception) {
                intent.putExtra(Constants.SESSION_EXPIRE_MSG, /*"${e.message}"*/
                    context.getString(R.string.session_expired))
            }
            if (session.isLoggedIn) {
                session.logout()
                intent.putExtra(Constants.UNAUTHORIZED, response.code)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                ContextCompat.startActivity(context, intent, null)
            }
        }
        return response
    }
}