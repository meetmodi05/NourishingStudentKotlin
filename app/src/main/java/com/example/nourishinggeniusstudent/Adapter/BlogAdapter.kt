package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvBlogLayoutBinding
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import com.example.nourishinggeniusstudent.ui.view.Blog.BlogActivity

class BlogAdapter(
    private val blogList: MutableList<BlogModel>
) : Adapter<BlogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RvBlogLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(blogList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, BlogActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(var binding: RvBlogLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: BlogModel) {
//            binding.blogImg1.setImageResource(blog.img!!)
            binding.blogTvTitle.text = blog.title
        }
    }
}