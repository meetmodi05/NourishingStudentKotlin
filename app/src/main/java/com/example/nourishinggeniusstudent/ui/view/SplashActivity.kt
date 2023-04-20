package com.example.nourishinggeniusstudent.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivitySplashBinding
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (session?.isLoggedIn == true) {

                Intent(this@SplashActivity, DashBoardActivity::class.java)
            } else {
                Intent(this@SplashActivity, LoginActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 1500)

    }
}