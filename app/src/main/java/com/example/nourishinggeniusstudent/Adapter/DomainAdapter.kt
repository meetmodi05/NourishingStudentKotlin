package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvBlogLayoutBinding
import com.example.nourishinggeniusstudent.databinding.RvDomainLayoutBinding
import com.example.nourishinggeniusstudent.model.BlogModel
import com.example.nourishinggeniusstudent.model.DomainModel
import com.example.nourishinggeniusstudent.ui.view.DashBoardActivity

class DomainAdapter(
    private val dashBoardActivity: DashBoardActivity,
    private val domainExpertList: ArrayList<DomainModel>
) : Adapter<DomainAdapter.DomainHolder>() {
    class DomainHolder(var binding: RvDomainLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: DomainModel) {
            binding.domainImg1.setImageResource(model.img!!)
            binding.domainTvTitle.text = model.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainHolder {
        return DomainHolder(
            RvDomainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return domainExpertList.size
    }

    override fun onBindViewHolder(holder: DomainHolder, position: Int) {
        holder.binding(domainExpertList[position])
    }
}