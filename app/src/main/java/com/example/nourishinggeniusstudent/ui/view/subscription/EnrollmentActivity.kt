package com.example.nourishinggeniusstudent.ui.view.subscription

import android.content.Intent
import android.os.Bundle
import com.example.nourishinggeniusstudent.databinding.ActivityEnrollmentBinding
import com.example.nourishinggeniusstudent.ui.adapter.OrdersAdapter
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity

class EnrollmentActivity : BaseActivity() {

    private lateinit var binding: ActivityEnrollmentBinding
    private val viewModel by lazy { SubscriptionViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnrollmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        binding.backAeroIconEnrollment.setOnClickListener { finish() }
        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this@EnrollmentActivity, GetCounsellingActivity::class.java))
        }
        session?.user?.userId?.let {
            viewModel.isLoading.value = true
            viewModel.getPaymentHistory(it)
        }
    }

    private fun setObservers() {
        viewModel.responsePaymentHistory.observe(this) {
            viewModel.isLoading.value = false
            binding.rvOrders.adapter = OrdersAdapter(it)
        }
    }
}