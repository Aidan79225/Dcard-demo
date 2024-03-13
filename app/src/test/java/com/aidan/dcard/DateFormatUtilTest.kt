package com.aidan.dcard

import com.aidan.dcard.util.DateFormatUtil
import org.junit.Assert
import org.junit.Test
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DateFormatUtilTest {
    private val dateFormatUtil = DateFormatUtil()
    @Test
    fun `verify on 30 mins diff`() {
        val a = Calendar.getInstance()
        val b = Calendar.getInstance().apply {
            timeInMillis = a.timeInMillis - TimeUnit.MINUTES.toMillis(30)
        }
        Assert.assertEquals(
            R.string.updated_repo to "30 mins ago",
            dateFormatUtil.createDateStringOfUpdated(a.time, b.time)
        )
    }

    @Test
    fun `verify on 9 hours diff`() {
        val a = Calendar.getInstance()
        val b = Calendar.getInstance().apply {
            timeInMillis = a.timeInMillis - TimeUnit.HOURS.toMillis(9)
        }
        Assert.assertEquals(
            R.string.updated_repo to "9 hours ago",
            dateFormatUtil.createDateStringOfUpdated(a.time, b.time)
        )
    }

    @Test
    fun `verify on 20 days diff`() {
        val a = Calendar.getInstance()
        val b = Calendar.getInstance().apply {
            timeInMillis = a.timeInMillis - TimeUnit.DAYS.toMillis(20)
        }
        Assert.assertEquals(
            R.string.updated_repo to "20 days ago",
            dateFormatUtil.createDateStringOfUpdated(a.time, b.time)
        )
    }

    @Test
    fun `verify on same year diff`() {
        val a = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, 10)
        }
        val b = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, 1)
            set(Calendar.DATE, 1)
        }
        Assert.assertEquals(
            R.string.updated_on_repo to "02-01",
            dateFormatUtil.createDateStringOfUpdated(a.time, b.time)
        )
    }

    @Test
    fun `verify on different year diff`() {
        val a = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, 10)
        }
        val b = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, 1)
            set(Calendar.DATE, 1)
        }
        Assert.assertEquals(
            R.string.updated_on_repo to "2023-02-01",
            dateFormatUtil.createDateStringOfUpdated(a.time, b.time)
        )
    }
}