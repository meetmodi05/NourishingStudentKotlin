package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.Gridlayout1Binding
import com.example.nourishinggeniusstudent.model.SuccessfulModel
import com.example.nourishinggeniusstudent.ui.view.DashBoardActivity

class SuccessfulAdapter(
    private val dashBoardActivity: DashBoardActivity,
    private val successfulList: ArrayList<SuccessfulModel>
) : Adapter<SuccessfulAdapter.ViewHolder>() {
    class ViewHolder(var binding: Gridlayout1Binding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: SuccessfulModel) {
            binding.iVIcon.setImageResource(model.image!!)
            binding.iVName.text = model.name
            Glide.with(binding.iVIcon).load(model.image).override(600, 512).into(binding.iVIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Gridlayout1Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return successfulList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(successfulList[position])
    }

}