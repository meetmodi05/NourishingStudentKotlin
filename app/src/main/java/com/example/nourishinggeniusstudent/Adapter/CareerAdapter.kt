package com.example.nourishinggeniusstudent.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.CareerLayoutBinding
import com.example.nourishinggeniusstudent.model.CareerModel
import com.example.nourishinggeniusstudent.ui.view.Career.CareerActivity
import com.example.nourishinggeniusstudent.ui.view.Career.CareerInfo

class CareerAdapter(
    private val context: CareerActivity,
    private val careerList: ArrayList<CareerModel>
) : Adapter<CareerAdapter.MyView>() {
    inner class MyView(var binding: CareerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: CareerModel) {
            val img = binding.img1.setImageResource(blog.img!!)
            binding.tvTitle3.text = blog.title
            Glide.with(binding.img1).load(blog.img).override(512, 312).into(binding.img1)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(
            CareerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return careerList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.binding(careerList[position])
        holder.itemView.setOnClickListener {
            val careerInfoIntent = Intent(context, CareerInfo::class.java)
            context.startActivity(careerInfoIntent)
        }

    }
}