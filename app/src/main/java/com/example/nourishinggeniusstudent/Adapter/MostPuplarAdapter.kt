package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nourishinggeniusstudent.databinding.MostPopularLayoutBinding
import com.example.nourishinggeniusstudent.model.MostPopularModel
import com.example.nourishinggeniusstudent.ui.view.DashBoardActivity

class MostPuplarAdapter(
    private val dashBoardActivity: DashBoardActivity,
    private val mostPopularList: ArrayList<MostPopularModel>
) : RecyclerView.Adapter<MostPuplarAdapter.ViewHolder>() {
    class ViewHolder(var binding: MostPopularLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: MostPopularModel) {
            binding.imageView.setImageResource(blog.mainImg!!)
            binding.imageView2.setImageResource(blog.icon!!)
            binding.tvDescription.text = blog.description
            binding.tvQTitle.text = blog.title
            binding.tvMiniTitle.text = blog.miniTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MostPopularLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return mostPopularList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(mostPopularList[position])
    }

}
