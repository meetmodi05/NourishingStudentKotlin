package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.ItemCaseStudyBinding
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.utils.getImageProgress

class CaseStudyAdapter(
    private val listener: (CaseStudyData) -> Unit
) : RecyclerView.Adapter<CaseStudyAdapter.ViewHolder>() {

    private val caseStudyList: ArrayList<CaseStudyData> = arrayListOf()

    fun setList(list: List<CaseStudyData>) {
        caseStudyList.clear()
        caseStudyList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCaseStudyBinding,val mContext : Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CaseStudyData) {
            binding.tvTitle.text = model.title
            Glide.with(binding.allDomainImg1).load(model.featureUri).override(525, 325).placeholder(mContext.getImageProgress())
                .into(binding.allDomainImg1)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCaseStudyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),parent.context
        )
    }

    override fun getItemCount(): Int {
        return caseStudyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(caseStudyList[position])
        holder.binding.root.setOnClickListener {
            listener(caseStudyList[position])
        }
    }

}
