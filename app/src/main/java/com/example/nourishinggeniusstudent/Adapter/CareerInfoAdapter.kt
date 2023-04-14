package com.example.nourishinggeniusstudent.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.nourishinggeniusstudent.databinding.CareerinfolayoutBinding
import com.example.nourishinggeniusstudent.model.CareerInfoDetail

class CareerInfoAdapter(private var careerInfoList: MutableList<CareerInfoDetail>) :
    Adapter<CareerInfoAdapter.CareerInfoHolder>() {
    class CareerInfoHolder(var binding: CareerinfolayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(blog: CareerInfoDetail) {
            binding.tvImpact.text = blog.impactTitle
            binding.tvDescriptionImpact.text = blog.careerImact
            binding.tvSpecialization.text = blog.specializationTitle
            binding.tvDescriptionSpl.text = blog.careerSpecialization
            binding.tvCareerPath.text = blog.careerTitle
            binding.tvDescriptionCareerPath.text = blog.careerPaths
            binding.tvAdvantageDisadvantage.text = blog.advantagesDisadvantagesTitle
            binding.tvDescriptionAdvantageDisadvantage.text = blog.careerAdvantagesAndDisadvantages
            binding.tvWork.text = blog.workTitle
            binding.tvDescriptionWork.text = blog.careerWork
            binding.tvWorkLifeBalance.text = blog.workLifeBalanceTitle
            binding.tvDescriptionWLB.text = blog.careerInvestmentEarningPotential
            binding.tvStrengths.text = blog.careerStrengthAndWeaknessesTitle
            binding.tvDescriptionStrengths.text = blog.careerStrengthAndWeaknesses
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareerInfoHolder {
        return CareerInfoHolder(
            CareerinfolayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return careerInfoList.size
    }

    override fun onBindViewHolder(holder: CareerInfoHolder, position: Int) {
        holder.binding(careerInfoList[position])
    }

}
