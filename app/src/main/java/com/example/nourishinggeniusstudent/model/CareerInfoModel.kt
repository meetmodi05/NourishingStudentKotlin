package com.example.nourishinggeniusstudent.model

import com.google.gson.annotations.SerializedName

class CareerInfoModel(
    val img: Int? = null,
    val designationImg: Int? = null,
    val title: String? = null,
    val designation: String? = null
)

data class CareerInfoDetail(
    @SerializedName("career_id") val careerId: Int? = null,
    @SerializedName("career_title") val careerTitle: String? = null,
    @SerializedName("career_logo") val careerLogo: String? = null,
    @SerializedName("impact_title") val impactTitle: String? = null,
    @SerializedName("career_imact") val careerImact: String? = null,
    @SerializedName("specialization_title") val specializationTitle: String? = null,
    @SerializedName("career_specialization") val careerSpecialization: String? = null,
    @SerializedName("path_title") val pathTitle: String? = null,
    @SerializedName("career_paths") val careerPaths: String? = null,
    @SerializedName("advantages_disadvantages_title") val advantagesDisadvantagesTitle: String? = null,
    @SerializedName("career_advantages_and_disadvantages") val careerAdvantagesAndDisadvantages: String? = null,
    @SerializedName("work_title") val workTitle: String? = null,
    @SerializedName("career_work") val careerWork: String? = null,
    @SerializedName("work_life_balance_title") val workLifeBalanceTitle: String? = null,
    @SerializedName("career_investment_earning_potential") val careerInvestmentEarningPotential: String? = null,
    @SerializedName("career_strength_and_weaknesses_title") val careerStrengthAndWeaknessesTitle: String? = null,
    @SerializedName("career_strength_and_weaknesses") val careerStrengthAndWeaknesses: String? = null,
)
