package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.CareerLayoutBinding
import com.example.nourishinggeniusstudent.model.careers.Careers
import com.example.nourishinggeniusstudent.model.careers.EducationDegree
import com.example.nourishinggeniusstudent.utils.getImageProgress

class DegreeAdapter(
    private val list: ArrayList<EducationDegree>,
    private val listener: (EducationDegree) -> Unit
) : Adapter<DegreeAdapter.MyView>() {
    inner class MyView(val mContext: Context, var binding: CareerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: EducationDegree) {
            binding.tvTitle3.text = data.title
            Glide.with(mContext).load(data.logo).override(512, 312).placeholder(mContext.getImageProgress()).into(binding.img1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(
            parent.context,
            CareerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.binding(list[position])
        holder.itemView.setOnClickListener {
            listener(list[position])
        }

    }
}