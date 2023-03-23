package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvBlogLayoutBinding
import com.example.nourishinggeniusstudent.model.data.BlogModel
import com.example.nourishinggeniusstudent.ui.view.blog.BlogActivity
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity

class RvBlogAdapter(
    private val context : Context, private val blogList: ArrayList<BlogModel>
) : Adapter<RvBlogAdapter.ViewHolder>() {
    class ViewHolder(var binding: RvBlogLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: BlogModel) {
            binding.blogImg1.setImageResource(model.img!!)
            binding.blogTvTitle.text = model.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvBlogLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(blogList[position])
        holder.itemView.setOnClickListener{
            val intent = Intent(context, BlogActivity::class.java)
            context.startActivity(intent)
        }
    }
}