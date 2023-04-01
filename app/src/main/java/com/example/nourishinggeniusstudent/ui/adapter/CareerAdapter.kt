package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.CareerLayoutBinding
import com.example.nourishinggeniusstudent.model.careers.Careers

class CareerAdapter(
    private val careerList: ArrayList<Careers>, private val listener: (Careers) -> Unit
) : Adapter<CareerAdapter.MyView>(), Filterable {

    val filteredList: ArrayList<Careers> = arrayListOf()

    inner class MyView(val mContext: Context, var binding: CareerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: Careers) {
            binding.tvTitle3.text = blog.career_title
            Glide.with(mContext).load(blog.career_logo).override(512, 312).into(binding.img1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(
            parent.context,
            CareerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.binding(filteredList[position])
        holder.itemView.setOnClickListener {
            listener(filteredList[position])
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                filteredList.clear()
                careerList.forEach {
                    if (it.career_title.lowercase().startsWith(p0.toString().lowercase())) {
                        filteredList.add(it)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }
}