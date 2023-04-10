package com.example.nourishinggeniusstudent.ui.viewModel.Packages

import androidx.lifecycle.MutableLiveData
import com.example.nourishinggeniusstudent.Adapter.PackageAdapter
import com.example.nourishinggeniusstudent.Networking.usecases.PackagesUseCase
import com.example.nourishinggeniusstudent.model.Packages.*
import com.example.nourishinggeniusstudent.ui.viewModel.BaseViewModel


class PackageViewModel : BaseViewModel() {
    private var packageList: MutableList<PackagesModel> = mutableListOf()
    private var packagesLiveData: MutableLiveData<List<PackagesModel>> = MutableLiveData()
    private var packagesAdapter: PackageAdapter = PackageAdapter(packageList)

    private val packagesUseCase by lazy { PackagesUseCase(errorLiveData, packagesLiveData) }

    fun init() {
        packagesAdapter = PackageAdapter(packageList)
        packagesLiveData.observeForever {
            if (!it.isNullOrEmpty()) {
                packageList.clear()
                packageList.addAll(it)
                packagesAdapter.notifyDataSetChanged()

                println("++++++++++careerList++++++++++$packageList")
            } else {
                println("List Has no rec")
            }
        }
    }

    fun packagesList() = packagesUseCase.packagesList()
    fun getPackagesAdapter(): PackageAdapter = packagesAdapter
}