package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvTopExpertLayoutBinding
import com.example.nourishinggeniusstudent.model.ExportPost
import com.example.nourishinggeniusstudent.model.TopExpertModel

class TopExpertAdapter(
    private val dashboardTopExpertList: MutableList<ExportPost>
) : RecyclerView.Adapter<TopExpertAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvTopExpertLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: ExportPost) {
            binding.topExpertTvTitle.text = blog.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvTopExpertLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(dashboardTopExpertList[position])
        Glide.with(holder.binding.topExpertImg1.context).load(dashboardTopExpertList[position].img)
            .override(312, 312).into(holder.binding.topExpertImg1)

    }

    override fun getItemCount(): Int {
        return dashboardTopExpertList.size
    }
}
