package com.example.nourishinggeniusstudent.model.data

import com.google.gson.annotations.SerializedName

data class PaymentHistory(
    @SerializedName("payment_id") var paymentId: Int? = null,
    @SerializedName("razorpay_id") var razorpayId: String? = null,
    @SerializedName("package_id") var packageId: String? = null,
    @SerializedName("package_name") var packageName: String? = null,
    @SerializedName("time_payment_made") var timePaymentMade: String? = null
) : java.io.Serializable
