package com.example.nourishinggeniusstudent.ui.view.subscription

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourishinggeniusstudent.databinding.ActivityGetCounsellingBinding
import com.example.nourishinggeniusstudent.model.data.Packages
import com.example.nourishinggeniusstudent.ui.adapter.PackageAdapter
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity
import com.example.nourishinggeniusstudent.ui.view.auth.LoginActivity.Companion.TAG
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject


class GetCounsellingActivity : BaseActivity(), PaymentResultListener, (Packages) -> Unit {
    private var checkout: Checkout? = null
    private var packageModel: Packages? = null
    private lateinit var binding: ActivityGetCounsellingBinding
    private val viewModel by lazy { SubscriptionViewModel(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCounsellingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        initRazorPay()
        setClickListners()
        setObservers()
    }

    private fun initRazorPay() {
        Checkout.preload(applicationContext)
        checkout = Checkout()
        checkout?.setKeyID(Constants.RAZOR_PAY_KEY)
    }

    private fun createOrder(packageModel: Packages) {
        try {
            val options = JSONObject()

            options.put("name", "NourishingGenius")
            options.put("description", "")
//            options.put(
//                "order_id", "${session?.user?.userId}_${System.currentTimeMillis()}"
//            ) //from response of step 3.

            options.put("currency", "INR")
            val amt = packageModel.packageCost?.removeRange(0, 4) + "00"
            options.put("amount", amt) //pass amount in currency subunits

            options.put("prefill.email", session?.user?.email)
//        options.put("prefill.contact", "9988776655")
            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 2)
            options.put("retry", retryObj)
            this.packageModel = packageModel
            checkout?.open(this, options)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    private fun setClickListners() {
        binding.backAeroIcon.setOnClickListener { finish() }
    }

    private fun setObservers() {
        viewModel.packages.observe(this) {
            binding.rvPackages.layoutManager = LinearLayoutManager(this)
            binding.rvPackages.adapter = PackageAdapter(this, it.packagesPosts, this)
            viewModel.isLoading.value = false
        }
        viewModel.responseCreatePayment.observe(this) {
            finish()
            viewModel.isLoading.value = false
        }
    }

    private fun initView() {
        viewModel.getSubscriptionPackages()
    }

    override fun invoke(p1: Packages) {
        createOrder(p1)
    }


    companion object {
        val TAG: String = GetCounsellingActivity::class.java.name
    }

    override fun onPaymentSuccess(p0: String?) {
//        showToastLong(p0.toString())
        viewModel.isLoading.value = true
        viewModel.createPayment(
            packageModel?.packageId.toString(), session?.user?.userId.toString(), p0.toString()
        )
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.e(TAG, p0.toString() + p1.toString())
    }

}