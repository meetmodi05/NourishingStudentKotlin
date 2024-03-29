package com.example.nourishinggeniusstudent.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.databinding.RvDomainAllLayoutBinding
import com.example.nourishinggeniusstudent.model.CaseStudiesModel
import com.example.nourishinggeniusstudent.model.CaseStudyPost
import com.example.nourishinggeniusstudent.ui.view.Domain.DomainActivity

class CaseStudiesAdapter(
    private val caseStudiesList: MutableList<CaseStudyPost>
) : RecyclerView.Adapter<CaseStudiesAdapter.CaseStudiesHolder>() {

    class CaseStudiesHolder(var binding: RvDomainAllLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(model: CaseStudyPost) {
            binding.allDomainTvTitle.text = model.cstitle
            

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseStudiesHolder {
        return CaseStudiesHolder(
            RvDomainAllLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return caseStudiesList.size
    }

    override fun onBindViewHolder(holder: CaseStudiesHolder, position: Int) {
        holder.binding(caseStudiesList[position])
        Glide.with(holder.binding.allDomainImg1.context).load(caseStudiesList[position].image)
            .override(312, 312).into(holder.binding.allDomainImg1)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DomainActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

}
