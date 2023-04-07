package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.MostPopularLayoutBinding
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.model.data.BlogModel

class BlogAdapter(
    private val blogList: ArrayList<BlogDataModel>, private val listener: (BlogDataModel) -> Unit
) : Adapter<BlogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            parent.context, MostPopularLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(blogList[position])
        holder.binding.root.setOnClickListener {
            listener(blogList[position])
        }
    }

    class MyViewHolder(val mContext: Context, var binding: MostPopularLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: BlogDataModel) {
            Glide.with(mContext).load(blog.postImageUrl).centerCrop().placeholder(
                ContextCompat.getDrawable(
                    mContext, R.drawable.img_1
                )
            ).centerCrop().into(binding.imageView)
            binding.tvMiniTitle.text = blog.postName
//            binding.tvDescription.text = blog.description
        }
    }
}