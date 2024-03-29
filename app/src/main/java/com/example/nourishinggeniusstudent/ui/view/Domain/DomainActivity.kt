package com.example.nourishinggeniusstudent.ui.view.Domain

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nourishinggeniusstudent.Adapter.AllDomainAdapter
import com.example.nourishinggeniusstudent.R
import com.example.nourishinggeniusstudent.databinding.ActivityDomainBinding
import com.example.nourishinggeniusstudent.model.TopExpertModel

class DomainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDomainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backAeroIcon.setOnClickListener { finish() }


//        val topExpertList = arrayListOf<TopExpertModel>()
//
//        topExpertList.add(TopExpertModel(R.drawable.suit_icon_vector, "Kunal Shah", "Entrepreneur"))
//        topExpertList.add(TopExpertModel(R.drawable.microsscope, "Jay Dosi", "Scientist"))
//        topExpertList.add(TopExpertModel(R.drawable.law_icon, "Akshat Dave", "Lawyer"))
//        topExpertList.add(TopExpertModel(R.drawable.engineering, "Rahul Patel", "Engineer"))
//
//        binding.rvBLogList.layoutManager = GridLayoutManager(this, 2)
//        binding.rvBLogList.adapter = AllDomainAdapter(this, topExpertList)

        val dropList = arrayListOf<String>()
        dropList.add("Entrepreneur")
        dropList.add("Lawyer")
        dropList.add("Scientist")
        dropList.add("Engineer")
        val aa = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dropList)
        binding.autoCompleteTV.setAdapter(aa)
    }
}