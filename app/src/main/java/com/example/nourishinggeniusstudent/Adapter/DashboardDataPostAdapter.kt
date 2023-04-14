package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.DatapostdblayoutBinding
import com.example.nourishinggeniusstudent.model.DefaultDataPost

class DashboardDataPostAdapter(private val list: MutableList<DefaultDataPost>) :
    Adapter<DashboardDataPostAdapter.DataPostHolder>() {
    class DataPostHolder(var binding: DatapostdblayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(item: DefaultDataPost) {
            binding.tvTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPostHolder {
        return DataPostHolder(
            DatapostdblayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataPostHolder, position: Int) {
        holder.binding(list[position])
        Glide.with(holder.binding.ivPostImg.context).load(list[position].img).override(312, 312)
            .into(holder.binding.ivPostImg)
    }
}