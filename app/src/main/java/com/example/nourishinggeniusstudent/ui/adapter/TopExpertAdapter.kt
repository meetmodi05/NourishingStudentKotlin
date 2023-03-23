package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvTopExpertLayoutBinding
import com.example.nourishinggeniusstudent.model.data.TopExpertModel
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class TopExpertAdapter(
    private val context: Context, private val topExpertList: ArrayList<TopExpertModel>
) : RecyclerView.Adapter<TopExpertAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: RvTopExpertLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: TopExpertModel) {
            binding.topExpertImg1.setImageResource(blog.img!!)
            binding.topExpertTvTitle.text = blog.title
            Glide.with(binding.topExpertImg1).load(topExpertList[position].img).override(600, 512)
                .into(binding.topExpertImg1)
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
    }

    override fun getItemCount(): Int {
        return topExpertList.size
    }

}
