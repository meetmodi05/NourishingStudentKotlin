package com.example.nourishinggeniusstudent.networking.usecases

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.networking.network.CallbackObserver
import com.example.nourishinggeniusstudent.networking.network.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class DomainUsecase(
    val context: Context,
    private var errorLiveData: MutableLiveData<String>,
    private var domainRoles: MutableLiveData<List<SortRoles>>? = null,
    private var domainList: MutableLiveData<List<DomainData>>? = null,
    private var domainData: MutableLiveData<DomainData>? = null,
) {

    fun getDomainRoles() {
        if (domainRoles == null) return
        Networking.with(context).getServices().getDomainRoles(
            role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<SortRoles>>>() {
                override fun onSuccess(response: BaseModel<List<SortRoles>>) {
                    domainRoles?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getDomainData(id: Int?) {
        if (domainRoles == null) return
        Networking.with(context).getServices().getDomainData(
            id = id.toString().toRequestBody(), role = "student".toRequestBody()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<DomainData>>() {
                override fun onSuccess(response: BaseModel<DomainData>) {
                    domainData?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

    fun getDomainList(taxId: Int? = null, search: String = "") {
        if (domainRoles == null) return
        val request = HashMap<String, RequestBody>()
        request["role"] = "student".toRequestBody()
        if (taxId != null) {
            request["tax_id"] = taxId.toString().toRequestBody()
        }
        if (search.isNotBlank()) {
            request["search_term"] = search.toRequestBody()
        }

        Networking.with(context).getServices().getDomainList(
            request
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallbackObserver<BaseModel<List<DomainData>>>() {
                override fun onSuccess(response: BaseModel<List<DomainData>>) {
                    domainList?.value = response.data
                }

                override fun onFailed(code: Int, message: String) {
                    errorLiveData.value = message
                }

            })
    }

}