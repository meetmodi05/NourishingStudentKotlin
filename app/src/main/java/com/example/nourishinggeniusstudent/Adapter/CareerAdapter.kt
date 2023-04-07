package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.CareerLayoutBinding
import com.example.nourishinggeniusstudent.model.CareerPost
import com.example.nourishinggeniusstudent.ui.view.Career.CareerInfo
import com.example.nourishinggeniusstudent.ui.viewModel.Career.CareerViewModel

class CareerAdapter(
    private val list: MutableList<CareerPost>
) : Adapter<CareerAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(
            CareerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val cPost = CareerPost()
        holder.binding(list[position])
        Glide.with(holder.binding.img1.context).load(list[position].img).override(312, 312)
            .into(holder.binding.img1)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CareerInfo::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class MyView(var binding: CareerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(item: CareerPost) {
            binding.tvTitle.text = item.careerTitle
        }
    }
}