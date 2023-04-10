package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.RvBlogLayoutBinding
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import com.example.nourishinggeniusstudent.ui.view.BlogActivity
import com.example.nourishinggeniusstudent.ui.view.DashBoardActivity

class RvBlogAdapter(
    private val dashBoardActivity: DashBoardActivity, private val blogList: ArrayList<BlogModel>
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
            val intent = Intent(dashBoardActivity, BlogActivity::class.java)
            dashBoardActivity.startActivity(intent)
        }
    }
}