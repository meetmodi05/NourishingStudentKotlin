package com.example.nourishinggeniusstudent.ui.view.career

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityCareerInfoBinding
import com.example.nourishinggeniusstudent.model.careers.CareerDetails
import com.example.nourishinggeniusstudent.ui.view.base.BaseActivity
import com.example.nourishinggeniusstudent.utils.Constants
import com.example.nourishinggeniusstudent.utils.getImageProgress

class CareerInfo : BaseActivity() {
    private lateinit var binding: ActivityCareerInfoBinding
    private val viewModel by lazy { CareerViewModel(this@CareerInfo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.value = true
        initView()
        setObservers()
        setClickListeners()

    }

    private fun setObservers() {
        viewModel.careerDetails.observe(this) {
            setData(it)
            viewModel.isLoading.value = false
        }
    }

    private fun setData(details: CareerDetails?) {
        details?.apply {
            Glide.with(this@CareerInfo).load(careerLogo).placeholder(getImageProgress()).into(binding.ivCareer)
            binding.tvCareerTitle.text = careerTitle
            binding.layoutImpact.tvTitle.text = getString(R.string.impact)
            binding.layoutImpact.tvDetails.text =
                Html.fromHtml(careerImact, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutSpecialization.tvTitle.text = getString(R.string.specialization)
            binding.layoutSpecialization.tvDetails.text =
                Html.fromHtml(careerSpecialization, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutCareerPaths.tvTitle.text = getString(R.string.career_paths)
            binding.layoutCareerPaths.tvDetails.text =
                Html.fromHtml(careerPaths, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutAdvantages.tvTitle.text = getString(R.string.adv_dis)
            binding.layoutAdvantages.tvDetails.text =
                Html.fromHtml(careerAdvantagesAndDisadvantages, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutWork.tvTitle.text = getString(R.string.work)
            binding.layoutWork.tvDetails.text =
                Html.fromHtml(careerWork, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutWorkLife.tvTitle.text = getString(R.string.work_lifeB)
            binding.layoutWorkLife.tvDetails.text = getString(R.string.lorem_ipsum)
            binding.layoutInvestment.tvTitle.text = getString(R.string.inv)
            binding.layoutInvestment.tvDetails.text =
                Html.fromHtml(careerInvestmentEarningPotential, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.layoutStrWeak.tvTitle.text = getString(R.string.str_weak)
            binding.layoutStrWeak.tvDetails.text =
                Html.fromHtml(careerStrengthAndWeaknesses, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun initView() {
        viewModel.getCareerDetails(intent.getIntExtra(Constants.CAREER_ID, 0))
    }

    private fun setClickListeners() {
        binding.backAeroIcon.setOnClickListener { finish() }
        binding.layoutImpact.tvTitle.setOnClickListener {
            binding.layoutImpact.tvDetails.isVisible = !binding.layoutImpact.tvDetails.isVisible
        }
        binding.layoutSpecialization.tvTitle.setOnClickListener {
            binding.layoutSpecialization.tvDetails.isVisible =
                !binding.layoutSpecialization.tvDetails.isVisible
        }
        binding.layoutCareerPaths.tvTitle.setOnClickListener {
            binding.layoutCareerPaths.tvDetails.isVisible =
                !binding.layoutCareerPaths.tvDetails.isVisible
        }
        binding.layoutAdvantages.tvTitle.setOnClickListener {
            binding.layoutAdvantages.tvDetails.isVisible =
                !binding.layoutAdvantages.tvDetails.isVisible
        }
        binding.layoutWork.tvTitle.setOnClickListener {
            binding.layoutWork.tvDetails.isVisible = !binding.layoutWork.tvDetails.isVisible
        }
        binding.layoutWorkLife.tvTitle.setOnClickListener {
            binding.layoutWorkLife.tvDetails.isVisible = !binding.layoutWorkLife.tvDetails.isVisible
        }
        binding.layoutInvestment.tvTitle.setOnClickListener {
            binding.layoutInvestment.tvDetails.isVisible =
                !binding.layoutInvestment.tvDetails.isVisible
        }
        binding.layoutStrWeak.tvTitle.setOnClickListener {
            binding.layoutStrWeak.tvDetails.isVisible = !binding.layoutStrWeak.tvDetails.isVisible
        }
    }

}