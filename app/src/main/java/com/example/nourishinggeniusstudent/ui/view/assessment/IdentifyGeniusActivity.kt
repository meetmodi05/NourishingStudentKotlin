package com.example.nourishinggeniusstudent.ui.view.assessment

import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityIdentifyGeniusBinding
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import java.util.*


class IdentifyGeniusActivity : BaseActivity() {
    private lateinit var binding: ActivityIdentifyGeniusBinding

//    val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
//        BetterActivityResult.registerActivityForResult(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyGeniusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backAeroIcon.setOnClickListener { finish() }
        val name64: String = Base64.getEncoder().encodeToString(session?.user?.name?.toByteArray())
        val email64: String =
            Base64.getEncoder().encodeToString(session?.user?.email?.toByteArray())
        val testCode64: String = Base64.getEncoder().encodeToString("5450700001".toByteArray())
        /*StartTestService.callTestLinkServiceForData(
            this,
            Constants.THINK_EXAM_CLIENT_URL,
            name64,
            email64,
            "Career Guidance Program",
            "",
            "",
            "120",
            session?.user?.profilePic,
            testCode64,
            activityLauncher
        )*/
    }
}