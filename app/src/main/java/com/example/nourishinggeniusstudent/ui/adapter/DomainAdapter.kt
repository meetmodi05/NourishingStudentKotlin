package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvDomainLayoutBinding
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.data.DomainModel
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.ui.view.domain.DomainActivity
import com.example.nourishinggeniusstudent.utils.getImageProgress

class DomainAdapter(
    private val listener: (DomainData) -> Unit
) : Adapter<DomainAdapter.DomainHolder>() {

    private val domainExpertList: ArrayList<DomainData> = arrayListOf()

    fun setList(list: List<DomainData>) {
        domainExpertList.clear()
        domainExpertList.addAll(list)
        notifyDataSetChanged()
    }
    class DomainHolder(var binding: RvDomainLayoutBinding,val mContext:Context) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: DomainData) {
            Glide.with(binding.domainImg1).load(model.featureUri).override(525, 325)
                .placeholder(mContext.getImageProgress()).into(binding.domainImg1)
            binding.domainTvTitle.text = model.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainHolder {
        return DomainHolder(
            RvDomainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),parent.context
        )
    }

    override fun getItemCount(): Int {
        return domainExpertList.size
    }

    override fun onBindViewHolder(holder: DomainHolder, position: Int) {
        holder.binding(domainExpertList[position])
        holder.itemView.setOnClickListener {
            listener(domainExpertList[position])
        }
    }
}