package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.MostPopularLayoutBinding
import com.example.nourishinggeniusstudent.model.MostPopularModel
import com.example.nourishinggeniusstudent.ui.view.BlogActivity

class BlogAdapter(
    private val blogActivity: BlogActivity,
    private val blogList: ArrayList<MostPopularModel>
) : Adapter<BlogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MostPopularLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(blogList[position])
    }

    class MyViewHolder(var binding: MostPopularLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: MostPopularModel) {
            binding.imageView.setImageResource(blog.mainImg!!)
            binding.icon.setImageResource(blog.icon!!)
            binding.tvDescription.text = blog.description
            binding.tvQTitle.text = blog.title
            binding.tvMiniTitle.text = blog.miniTitle
        }
    }
}