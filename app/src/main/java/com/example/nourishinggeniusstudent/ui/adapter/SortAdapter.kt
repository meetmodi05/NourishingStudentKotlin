package com.example.nourishinggeniusstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles


class SortAdapter(
    mContext: Context,
    val list: List<SortRoles>,
    private val resourceId: Int = androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
) : ArrayAdapter<SortRoles>(mContext, resourceId) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): SortRoles {
        return list[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val vi = context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater
            view = vi.inflate(resourceId, null)
        }

        val textView1 = view?.findViewById<TextView>(android.R.id.text1)
        textView1?.text = getItem(position).termName

        return view!!
    }

}