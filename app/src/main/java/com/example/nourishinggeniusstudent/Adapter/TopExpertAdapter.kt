package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvTopExpertLayoutBinding
import com.example.nourishinggeniusstudent.model.TopExpertModel
import com.example.nourishinggeniusstudent.ui.view.DashBoardActivity

class TopExpertAdapter(
    private val topExpertList: MutableList<TopExpertModel>
) : RecyclerView.Adapter<TopExpertAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvTopExpertLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: TopExpertModel) {
            binding.topExpertTvTitle.text = blog.title
//
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
        holder.binding(topExpertList[position])
        Glide.with(holder.binding.topExpertImg1.context).load(topExpertList[position].img)
            .override(312, 312).into(holder.binding.topExpertImg1)

    }

    override fun getItemCount(): Int {
        return topExpertList.size
    }

}
