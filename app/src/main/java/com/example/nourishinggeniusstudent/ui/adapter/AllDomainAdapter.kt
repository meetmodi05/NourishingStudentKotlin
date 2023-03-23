package com.example.nourishinggeniusstudent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvDomainAllLayoutBinding
import com.example.nourishinggeniusstudent.model.data.TopExpertModel
import com.example.nourishinggeniusstudent.ui.view.domain.DomainActivity

class AllDomainAdapter(
    private val domainActivity: DomainActivity, private val topExpertList: ArrayList<TopExpertModel>
) : RecyclerView.Adapter<AllDomainAdapter.ViewHolder>() {
    class ViewHolder(val binding: RvDomainAllLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(topExpertModel: TopExpertModel) {
            binding.allDomainImg1.setImageResource(topExpertModel.img!!)
            binding.allDomainTvTitle.text = topExpertModel.title
            binding.allDomainTvDesignation.text = topExpertModel.designation
            Glide.with(binding.allDomainImg1).load(topExpertModel.img).override(525, 325)
                .into(binding.allDomainImg1)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvDomainAllLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return topExpertList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(topExpertList[position])
    }

}
