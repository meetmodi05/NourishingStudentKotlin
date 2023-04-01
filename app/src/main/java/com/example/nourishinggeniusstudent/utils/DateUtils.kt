package com.example.nourishinggeniusstudent.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object DateUtils {

    private val TAG = DateUtils::class.java.name
    const val DD_MMM = "dd MMM"
    const val DD_MMM_YYYY = "dd MMM yyyy"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DATE_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    @SuppressLint("SimpleDateFormat")
    fun getDateFormat(timeInMillis: Long, format: String = DD_MM_YYYY): String {
        val df = SimpleDateFormat(format)
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeInMillis
        return df.format(cal.time)
    }

    fun getDate(date: String, format: String = DD_MM_YYYY): Date {
        val df = SimpleDateFormat(format)
        return df.parse(date)
    }

    fun changeDateFormat(
        date: String, newFormat: String = DD_MMM_YYYY
    ): String {
        val oldFormat = isValidDateFormat(date)
        Log.e(TAG, "changeDateFormat: $date $oldFormat")
        val oldDf = SimpleDateFormat(oldFormat)
        val newDf = SimpleDateFormat(newFormat)
        return try {
            oldDf.parse(date)?.let { newDf.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    private fun isValidDateFormat(date: String): String {
        return try {
            val first = SimpleDateFormat(YYYY_MM_DD)
            first.isLenient = false
            first.parse(date)
            YYYY_MM_DD
        } catch (e: ParseException) {
            try {
                val second = SimpleDateFormat(DD_MM_YYYY)
                second.isLenient = false
                second.parse(date)
                DD_MM_YYYY
            } catch (e: ParseException) {
                ""
            }
        }
    }
}
