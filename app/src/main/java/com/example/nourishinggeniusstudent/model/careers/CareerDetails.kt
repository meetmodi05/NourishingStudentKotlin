package com.example.nourishinggeniusstudent.model.careers

import com.google.gson.annotations.SerializedName

data class CareerDetails(
    @SerializedName("career_id") var careerId: Int? = null,
    @SerializedName("career_title") var careerTitle: String? = null,
    @SerializedName("career_logo") var careerLogo: String? = null,
    @SerializedName("impact_title") var impactTitle: String? = null,
    @SerializedName("career_imact") var careerImact: String? = null,
    @SerializedName("specialization_title") var specializationTitle: String? = null,
    @SerializedName("career_specialization") var careerSpecialization: String? = null,
    @SerializedName("path_title") var pathTitle: String? = null,
    @SerializedName("career_paths") var careerPaths: String? = null,
    @SerializedName("advantages_disadvantages_title") var advantagesDisadvantagesTitle: String? = null,
    @SerializedName("career_advantages_and_disadvantages") var careerAdvantagesAndDisadvantages: String? = null,
    @SerializedName("work_title") var workTitle: String? = null,
    @SerializedName("career_work") var careerWork: String? = null,
    @SerializedName("work_life_balance_title") var workLifeBalanceTitle: String? = null,
    @SerializedName("career_work_life_balance") var careerWorkLifeBalance: String? = null,
    @SerializedName("career_investment_earning_potential") var careerInvestmentEarningPotential: String? = null,
    @SerializedName("career_strength_and_weaknesses_title") var careerStrengthAndWeaknessesTitle: String? = null,
    @SerializedName("career_strength_and_weaknesses") var careerStrengthAndWeaknesses: String? = null
) : java.io.Serializable
