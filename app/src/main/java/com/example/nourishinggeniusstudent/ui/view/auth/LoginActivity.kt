package com.example.nourishinggeniusstudent.ui.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityLoginBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity() {
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
    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initGoogleSignin() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.WEB_CLIENT_ID).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }


    private fun setOnClickListeners() {
        binding.signUpBtn.setOnClickListener {
            var signUpIntent = Intent(this, SignupActivity::class.java)
            startActivity(signUpIntent)
        }
        binding.forgotPasswordTV.setOnClickListener {
            var forgotIntent = Intent(this, ForgotPassword::class.java)
            startActivity(forgotIntent)
        }
        binding.loginBtn.setOnClickListener {
            validate()
        }
        binding.btnGoogle.setOnClickListener {
            loginWithGoogle()
        }
        binding.btnFacebook.setOnClickListener {
            loginWithFacebook()
        }
    }

    private fun loginWithGoogle() {
        googleSigninResult.launch(googleSignInClient?.signInIntent)
    }

    private fun loginWithFacebook() {

    }

    private fun setObservers() {
        viewModel.userData.observe(this) {
            session?.user = it
            viewModel.isLoading.value = false
            var dashboardIntent = Intent(this, DashBoardActivity::class.java)
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
        viewModel.loginUser(
            binding.etEmail.text.toString().trim().lowercase(), binding.etPassword.text.toString()
        )
    }

    companion object {
        val TAG: String = LoginActivity::class.java.name
    }

}