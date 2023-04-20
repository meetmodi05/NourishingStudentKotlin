package com.example.nourishinggeniusstudent.ui.view.assessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nourishinggeniusstudent.databinding.ActivityIdentifyGeniusBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.subscription.GetCounsellingActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.service.taas.Activities.StartTestService
import com.service.taas.Helpers.BetterActivityResult
import java.util.*


class IdentifyGeniusActivity : BaseActivity() {
    private lateinit var binding: ActivityIdentifyGeniusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyGeniusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }
        binding.btnBuy.setOnClickListener {
            startActivity(Intent(this, GetCounsellingActivity::class.java))
        }
    }

    companion object {
        private val TAG = this::class.java.name
    }

}