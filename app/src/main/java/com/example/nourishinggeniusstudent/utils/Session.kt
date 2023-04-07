package com.example.nourishinggeniusstudent.utils

import android.content.Context
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.model.auth.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class Session(val context: Context) {
    val pref =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = pref.contains(KEY_IS_LOGIN) && pref.getBoolean(KEY_IS_LOGIN, false)
        set(isLoggedIn) = storeDataByKey(KEY_IS_LOGIN, isLoggedIn)

    var user: User?
        get() {
            val gson = Gson()
            val json = getDataByKey(KEY_USER_INFO, "")
            return gson.fromJson(json, User::class.java)
        }
        set(user) {
            val gson = Gson()
            val json = gson.toJson(user)
            pref.edit().putString(KEY_USER_INFO, json).apply()
            isLoggedIn = true
        }

    @JvmOverloads
    fun getDataByKey(Key: String, DefaultValue: String = ""): String {
        return if (pref.contains(Key)) {
            pref.getString(Key, DefaultValue).toString()
        } else {
            DefaultValue
        }
    }

    @JvmOverloads
    fun getDataByKey(Key: String, DefaultValue: Boolean = false): Boolean {
        return if (pref.contains(Key)) {
            pref.getBoolean(Key, DefaultValue)
        } else {
            DefaultValue
        }
    }

    fun storeDataByKey(key: String, Value: String) {
        pref.edit().putString(key, Value).apply()
    }

    fun storeDataByKey(key: String, Value: Boolean) {
        pref.edit().putBoolean(key, Value).apply()
    }

    operator fun contains(key: String): Boolean {
        return pref.contains(key)
    }

    fun remove(key: String) {
        pref.edit().remove(key).apply()
    }

    fun logout() {
        if (isLoggedIn) {
            pref.edit().clear().apply()
            val auth = Firebase.auth
            if(auth.currentUser !=null) {
                auth.signOut()
            }
        }
    }

    fun getToken(): String? {
        return user?.token
    }

    private companion object {
        private const val KEY_IS_LOGIN = "isLogin"
        private const val KEY_USER_INFO = "user"
        private const val KEY_SAVED_USER_LOGIN = "savedLogin"
    }
}