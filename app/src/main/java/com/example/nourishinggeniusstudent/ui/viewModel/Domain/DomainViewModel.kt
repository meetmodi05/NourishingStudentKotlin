package com.example.nourishinggeniusstudent.ui.viewModel.Domain

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.DomainAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.DomainUseCase
import com.example.nourishinggeniusstudent.model.Domain.DomainModel
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel

class DomainViewModel : BaseViewModel() {
    private var domainList: MutableList<DomainModel> = mutableListOf()
    private var domainLiveData: MutableLiveData<List<DomainModel>> = MutableLiveData()
    private var domainAdapter: DomainAdapter = DomainAdapter(domainList)

    private val domainUseCase by lazy { DomainUseCase(errorLiveData, domainLiveData) }

    fun init() {
        domainAdapter = DomainAdapter(domainList)
        domainLiveData.observeForever {
            if (!it.isNullOrEmpty()) {
                domainList.clear()
                domainList.addAll(it)
                domainAdapter.notifyDataSetChanged()
            }
        }
    }

    fun getDomainList() = domainUseCase.domainList()
    fun getDomainAdapter(): DomainAdapter = domainAdapter
}