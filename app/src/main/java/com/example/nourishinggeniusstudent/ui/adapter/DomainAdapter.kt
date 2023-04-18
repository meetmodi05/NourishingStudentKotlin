package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.RvDomainLayoutBinding
import com.example.nourishinggeniusstudent.model.domain.DomainData

class DomainAdapter(
    private val listener: (DomainData) -> Unit
) : Adapter<DomainAdapter.DomainHolder>() {

    private val domainExpertList: ArrayList<DomainData> = arrayListOf()

    fun setList(list: List<DomainData>) {
        domainExpertList.clear()
        domainExpertList.addAll(list)
        notifyDataSetChanged()
    }

    class DomainHolder(val mContext: Context, var binding: RvDomainLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(model: DomainData) {
            Glide.with(binding.domainImg1).load(model.featureUri).placeholder(
                ContextCompat.getDrawable(mContext, R.drawable.img_1))
                .into(binding.domainImg1)
            binding.domainTvTitle.text = model.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainHolder {
        return DomainHolder(
            parent.context,
            RvDomainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return domainExpertList.size
    }

    override fun onBindViewHolder(holder: DomainHolder, position: Int) {
        holder.binding(domainExpertList[position])
        holder.itemView.setOnClickListener {
            listener(domainExpertList[position])
        }
    }
}