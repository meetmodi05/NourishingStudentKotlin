package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvDomainLayoutBinding
import com.example.nourishinggeniusstudent.model.Domain.DomainModel
import com.example.nourishinggeniusstudent.ui.view.Domain.DomainActivity

class DomainAdapter(
    private val domainExpertList: MutableList<DomainModel>
) : Adapter<DomainAdapter.DomainHolder>() {
    class DomainHolder(var binding: RvDomainLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: DomainModel) {
//            binding.domainImg1.setImageResource(model.!!)
            binding.domainTvTitle.text = model.termName
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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DomainActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }
}