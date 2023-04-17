package com.example.nourishinggeniusstudent.ui.view.Career


import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.example.nourishinggeniusstudent.databinding.ActivityCareerInfoBinding
import com.example.nourishinggeniusstudent.ui.viewModel.Career.CareerInfoViewModel

class CareerInfo : AppCompatActivity() {
    private lateinit var binding: ActivityCareerInfoBinding
    private val viewModel by lazy { CareerInfoViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }

        viewModel.getCareerInfoList()
        viewModel.careerInfoLiveData.observe(this) {
            binding.tvTitle1.text = it.impactTitle
            binding.tvDescriptionImpact.text = it.careerImact
            binding.tvSpecialization.text = it.specializationTitle
            binding.tvDescriptionSpl.text = it.careerSpecialization
            binding.tvCareerPath.text = it.careerTitle
            binding.tvDescriptionCareerPath.text = it.careerPaths
            binding.tvAdvantageDisadvantage.text = it.advantagesDisadvantagesTitle
            binding.tvDescriptionAdvantageDisadvantage.text = it.careerAdvantagesAndDisadvantages
            binding.tvWork.text = it.workTitle
            binding.tvDescriptionWork.text = it.careerWork
            binding.tvWorkLifeBalance.text = it.workLifeBalanceTitle
            binding.tvDescriptionWLB.text = it.careerInvestmentEarningPotential
            binding.tvStrengths.text = it.careerStrengthAndWeaknessesTitle
            binding.tvDescriptionStrengths.text = it.careerStrengthAndWeaknesses
            Html.fromHtml(binding.tvDescriptionImpact.toString()).toString()
        }
    }
}