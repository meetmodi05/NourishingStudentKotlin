package com.example.nourishinggeniusstudent.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.regex.Pattern


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun EditText.getValue(): String {
    return this.text.toString().trim()
}

fun TextView.getValue(): String {
    return this.text.toString().trim()
}

fun EditText.isEmpty(): Boolean {
    return this.text.trim().isEmpty()
}

fun TextView.isEmpty(): Boolean {
    return this.text.trim().isEmpty()
}

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Activity.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun String.isEmail() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPhone() = android.util.Patterns.PHONE.matcher(this).matches()

fun String.isValidPassword(): Boolean {
    val pattern = Pattern.compile(Constants.PASSWORD_REGEX)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.toRequestBody(): RequestBody {
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}
fun String.getUrlName(): String {
    return this.subSequence(this.lastIndexOf("/") + 1, this.length).toString()
}

fun AppCompatEditText.placeCursorToEnd() {
    this.setSelection(this.getValue().length)
}

fun Context.getImageProgress(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

