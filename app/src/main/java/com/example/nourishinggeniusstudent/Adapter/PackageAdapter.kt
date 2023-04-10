package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvpackageslayoutBinding
import com.example.nourishinggeniusstudent.model.Packages.*
import com.example.nourishinggeniusstudent.ui.view.GetCounsellingActivity

class PackageAdapter(
    private val packageList: MutableList<PackagesModel>
) : Adapter<PackageAdapter.ViewHolder>() {
    class ViewHolder(var binding: RvpackageslayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: PackagesModel) {

            binding.careerSelection.text = blog.packageTitle
            binding.tvPackageCost.text = blog.packageCost
            binding.tvTitle.text = blog.packageContent
            binding.btnBuyNow.text = blog.buttonText
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvpackageslayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(packageList[position])
    }

    override fun getItemCount(): Int {
        return packageList.size
    }
}