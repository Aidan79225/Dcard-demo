package com.aidan.dcard.util

import com.aidan.dcard.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class DateFormatUtil {
    private val overYearDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private val sameYearDateFormat = SimpleDateFormat("MM-dd", Locale.ENGLISH)

    fun createDateStringOfUpdated(currentDate: Date, target: Date): Pair<Int, String> {
        val diffTime = currentDate.time - target.time
        return if (diffTime < TimeUnit.HOURS.toMillis(1)) {
            val mins = diffTime / TimeUnit.MINUTES.toMillis(1)
            R.string.updated_repo to "$mins mins ago"
        } else if (diffTime < TimeUnit.DAYS.toMillis(1)) {
            val days = diffTime / TimeUnit.HOURS.toMillis(1)
            R.string.updated_repo to "$days hours ago"
        } else if (diffTime < TimeUnit.DAYS.toMillis(30)) {
            val days = diffTime / TimeUnit.DAYS.toMillis(1)
            R.string.updated_repo to "$days days ago"
        } else if (isSameYear(currentDate, target)) {
            R.string.updated_on_repo to sameYearDateFormat.format(target)
        } else {
            R.string.updated_on_repo to overYearDateFormat.format(target)
        }
    }

    fun isSameYear(currentDate: Date, target: Date): Boolean {
        val currentYear = getYear(currentDate)
        val targetYear = getYear(target)
        return currentYear == targetYear
    }

    fun getYear(date: Date) = Calendar.getInstance().apply {
        time = date
    }.get(Calendar.YEAR)
}