package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.Gridlayout1Binding
import com.example.nourishinggeniusstudent.model.data.SuccessfulModel
import com.example.nourishinggeniusstudent.ui.view.home.DashBoardActivity
import com.example.nourishinggeniusstudent.utils.getImageProgress

class SuccessfulAdapter(
    private val context : Context,
    private val successfulList: ArrayList<SuccessfulModel>
) : Adapter<SuccessfulAdapter.ViewHolder>() {
    class ViewHolder(var binding: Gridlayout1Binding,val mContext : Context) : RecyclerView.ViewHolder(binding.root) {
        fun binding(model: SuccessfulModel) {
            binding.iVIcon.setImageResource(model.image!!)
            binding.iVName.text = model.name
            Glide.with(binding.iVIcon).load(model.image).override(600, 512).placeholder(mContext.getImageProgress()).into(binding.iVIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Gridlayout1Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),parent.context
        )
    }

    override fun getItemCount(): Int {
        return successfulList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(successfulList[position])
    }

}