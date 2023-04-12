package com.example.nourishinggeniusstudent.ui.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nourishinggeniusstudent.databinding.ItemOrderHistoryBinding
import com.example.nourishinggeniusstudent.model.data.PaymentHistory
import java.text.DateFormat
import java.util.*

class OrdersAdapter(private val list: List<PaymentHistory>) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemOrderHistoryBinding, private val mContext: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PaymentHistory) {
            binding.tvName.text = model.packageName
            binding.tvDate.text = model.timePaymentMade
            binding.tvAddTime.setOnClickListener {
                val mCalendar: Calendar = Calendar.getInstance()

                val year: Int = mCalendar.get(Calendar.YEAR)

                val month: Int = mCalendar.get(Calendar.MONTH)

                val dayOfMonth: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(
                    mContext, { view, year, month, dayOfMonth ->
                        val mCalendar = Calendar.getInstance()
                        mCalendar[Calendar.YEAR] = year
                        mCalendar[Calendar.MONTH] = month
                        mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                        val selectedDate: String =
                            DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.time)
//                        binding.tvDate.text = selectedDate
                    }, year, month, dayOfMonth
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}