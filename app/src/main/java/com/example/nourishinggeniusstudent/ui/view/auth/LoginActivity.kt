package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nourishinggeniusstudent.databinding.ActivityLoginBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.example.nourishinggeniusstudent.utils.showToast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity() {
    private var callbackManager: CallbackManager? = null
    private var googleSignInClient: GoogleSignInClient? = null
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy { AuthViewModel(this@LoginActivity) }

    private var mAuth: FirebaseAuth? = null

    private val googleSigninResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (signInAccountTask.isSuccessful) {
                    val data = signInAccountTask.result
                    viewModel.socialLogin(
                        data.displayName.toString(), data.email.toString(), data.id.toString(), " "
                    )
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance();
        setOnClickListeners()
        setObservers()
        initFirebase()
        initGoogleSignin()
        initFacebookSignin()
    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initGoogleSignin() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.WEB_CLIENT_ID).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun initFacebookSignin() {
        callbackManager = CallbackManager.Factory.create()
        binding.btnFacebookLogin.setPermissions("email")
        binding.btnFacebookLogin.registerCallback(
            callbackManager!!,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$loginResult")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                    showToast(error.localizedMessage)
                }
            })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")
                val user = mAuth?.currentUser
                viewModel.socialLogin(
                    user?.displayName.toString(), user?.email.toString(), user?.uid.toString(), " "
                )
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                showToast("Authentication failed.")
            }
        }
    }

    private fun setOnClickListeners() {
        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.forgotPasswordTV.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }
        binding.loginBtn.setOnClickListener {
            validate()
        }
        binding.btnGoogle.setOnClickListener {
            loginWithGoogle()
        }
        binding.btnFacebook.setOnClickListener {
            binding.btnFacebookLogin.performClick()
        }
    }

    private fun loginWithGoogle() {
        googleSigninResult.launch(googleSignInClient?.signInIntent)
    }

    private fun setObservers() {
        viewModel.userData.observe(this) {
            session?.user = it
            viewModel.isLoading.value = false
            val dashboardIntent = Intent(this, DashBoardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(dashboardIntent)
        }
    }

    private fun validate() {
        if (binding.etEmail.text.isNullOrBlank()) {
            return
        }
        if (binding.etPassword.text.isNullOrBlank()) {
            return
        }
        viewModel.isLoading.value = true
//        viewModel.loginUser(
//            binding.etEmail.text.toString().trim().lowercase(), binding.etPassword.text.toString()
//        )
    }

    companion object {
        val TAG: String = LoginActivity::class.java.name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}