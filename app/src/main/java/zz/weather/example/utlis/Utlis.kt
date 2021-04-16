package zz.weather.example.utlis

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @author zhangzheng
 * @Date  2021/4/14 8:28 下午
 * @ClassName Utlis
 * <p>
 * Desc :
 */
/**
 * 用于显示时间
 */
const val UTC_TIME = "yyyy-MM-dd'T'HH:mm+08:00"
const val TODAY = "今天"
const val TOMORROW = "明天"
val weeks = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")

fun isNight(): Boolean {
    val hour = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now())
    return (hour.toInt() in 0..5) || (hour.toInt() in 18..23)
}

fun formatDate(inputPattern: String, outputPattern: String, date: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.CHINESE)
    val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, Locale.CHINESE)
    if (inputPattern.contains("HH")) {
        val outputDate = LocalDateTime.parse(date, inputFormatter)
        return outputFormatter.format(outputDate)
    } else {
        val outputDate = LocalDate.parse(date, inputFormatter)
        return outputFormatter.format(outputDate)
    }
}


/**
 * 返回日期对应的周几
 * @param inputPattern  格式
 * @param time  "yyyy-mm-dd hh-dd"
 * @return
 */
fun dateToWeek(inputPattern: String, time: String): String {
    val today = DateTimeFormatter.ofPattern("dd").format(LocalDateTime.now()).toInt()
    val inputDay = formatDate(inputPattern, "dd", time).toInt()
    val diffDay = inputDay - today
    return if (diffDay < 0) {
        formatDate(inputPattern, "MM-dd", time)
    } else {
        showDateDetail(diffDay)
    }
}

/**
 * 将日期差显示为今天、明天或者星期
 * @param diffDay
 * @return
 */
private fun showDateDetail(diffDay: Int): String {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = Date()
    val i = calendar.get(Calendar.DAY_OF_WEEK) - 1
    return when (diffDay) {
        0 -> TODAY
        1 -> TOMORROW
        else -> getWeek(diffDay, i)
    }
}

private fun getWeek(diffDay: Int, today: Int): String {
    return if ((diffDay + today) < 7) {
        weeks[diffDay + today]
    } else {
        weeks[(diffDay + today) % 7]
    }

}

