package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvDomainLayoutBinding
import com.example.nourishinggeniusstudent.model.data.DomainModel
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.ui.view.domain.DomainActivity

class DomainAdapter(
    private val context : Context,
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
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DomainActivity::class.java)
            context.startActivity(intent)
        }
    }
}