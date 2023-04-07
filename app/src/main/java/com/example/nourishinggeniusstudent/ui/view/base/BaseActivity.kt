package com.example.nourishinggeniusstudent.ui.view.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.ui.dialogs.ProgressDialog
import com.example.nourishinggeniusstudent.utils.Session
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private var snackbar: Snackbar? = null
    var session: Session? = null
    private var progressDialog: ProgressDialog? = null
    var permissionListener: PermissionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = Session(this)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        dismissSnackBar()
        super.onDestroy()
    }

    fun dismissSnackBar() {
        snackbar?.dismiss()
    }

    fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showSnackbar(view: View, msg: String, LENGTH: Int) {
        snackbar = Snackbar.make(view, msg, LENGTH)
        val sbView = snackbar?.view
        val textView =
            sbView?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView?.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        textView?.maxLines = 4
        snackbar?.show()
    }

    fun showSoftKeyboard(editText: EditText?) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun showProgressbar() {
        hideProgressbar()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.show()
    }


    fun hideProgressbar() {
        progressDialog?.dismiss()
    }


    fun requestAppPermissions(
        requestedPermissions: Array<String?>,
        requestCode: Int, listener: PermissionListener?
    ) {
        permissionListener = listener
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        for (permission in requestedPermissions) {
            permissionCheck += ContextCompat.checkSelfPermission(this, permission.toString())
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, requestedPermissions, requestCode)
        } else {
            permissionListener?.onPermissionGranted(requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                permissionListener?.onPermissionGranted(requestCode)
                break
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                permissionListener?.onPermissionDenied(requestCode)
                break
            } else {
                permissionListener?.onPermissionNeverAsk(
                    requestCode
                )
                break
            }
        }
    }

    interface PermissionListener {
        fun onPermissionGranted(requestCode: Int)
        fun onPermissionDenied(requestCode: Int)
        fun onPermissionNeverAsk(requestCode: Int)
    }

}