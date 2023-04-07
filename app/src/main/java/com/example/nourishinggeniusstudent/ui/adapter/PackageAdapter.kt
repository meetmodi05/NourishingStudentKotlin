package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvpackageslayoutBinding
import com.example.nourishinggeniusstudent.model.data.Packages
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.ui.view.subscription.GetCounsellingActivity

class PackageAdapter(
    private val context: Context,
    private val packageList: ArrayList<Packages>,
    val listener: (Packages) -> Unit
) : Adapter<PackageAdapter.ViewHolder>() {
    class ViewHolder(var binding: RvpackageslayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(data: Packages) {
            binding.tvDescription.text = data.packageContent
            binding.careerSelection.text = "${data.packageTitle}\n${data.packageCost}"
            binding.btnBuyNow.text = data.buttonText
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
        holder.binding.btnBuyNow.setOnClickListener {
            if (packageList[position].buttonText == "Buy Now!") {
                listener(packageList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return packageList.size
    }
}