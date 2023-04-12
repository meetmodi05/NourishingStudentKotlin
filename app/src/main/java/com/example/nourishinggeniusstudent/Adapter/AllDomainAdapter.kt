package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvDomainAllLayoutBinding
import com.example.nourishinggeniusstudent.model.TopExpertModel
import com.example.nourishinggeniusstudent.ui.view.Domain.DomainActivity

class AllDomainAdapter(
    private val topExpertList: ArrayList<TopExpertModel>
) : RecyclerView.Adapter<AllDomainAdapter.ViewHolder>() {
    class ViewHolder(val binding: RvDomainAllLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(topExpertModel: TopExpertModel) {
//            binding.allDomainImg1.(topExpertModel.img!!)

            binding.allDomainTvTitle.text = topExpertModel.title
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
        Glide.with(holder.binding.allDomainImg1.context).load(topExpertList[position].img)
            .override(312, 312)
            .into(holder.binding.allDomainImg1)
    }

}
