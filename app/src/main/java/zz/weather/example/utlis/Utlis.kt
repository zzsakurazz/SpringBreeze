package zz.weather.example.utlis

import android.util.Log
import zz.weather.example.R
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


fun codeToIcon(code: Int): Int {
    return when (code) {
        100 , 150 -> {
            //晴天
            R.drawable.icon_100
        }
        in 101..103 -> {
            //多云
            R.drawable.icon_101
        }
        104 -> {
            //阴天
            R.drawable.icon_104
        }
        in 200..213 -> {
            //吹风
            R.drawable.icon_200
        }
        in 300..303 -> {
            //各种阵雨
            R.drawable.icon_300
        }
        304 -> {
            //阵雨加冰雹
            R.drawable.icon_304
        }
        305 , 309 -> {
            //小雨
            R.drawable.icon_305
        }
        306 -> {
            //中雨
            R.drawable.icon_306
        }
        307 -> {
            //大雨
            R.drawable.icon_307
        }
        308 -> {
            //暴雨
            R.drawable.icon_308
        }
        in 310..311 -> {
            //中雨？
            R.drawable.icon_310
        }
        312 -> {
            //中雨？
            R.drawable.icon_312
        }
        313 -> {
            //冻雨
            R.drawable.icon_313
        }
        400 -> {
            //小雪
            R.drawable.icon_400
        }
        401 -> {
            //中雪
            R.drawable.icon_401
        }
        402 -> {
            //大雪
            R.drawable.icon_402
        }
        403 -> {
            //大雪
            R.drawable.icon_403
        }
        in 404..406 -> {
            //雨夹雪?
            R.drawable.icon_404
        }
        407 -> {
            //特殊雪?
            R.drawable.icon_407
        }
        500 , 501 -> {
            //雾
            R.drawable.icon_500
        }
        502 -> {
            //霾
            R.drawable.icon_502
        }
        503 , 507 , 508 -> {
            //沙尘暴
            R.drawable.icon_503
        }
        504 -> {
            //沙尘暴
            R.drawable.icon_504
        }
        900 -> {
            //特别热
            R.drawable.icon_900
        }
        901 -> {
            //特别冷
            R.drawable.icon_901
        }
        else -> {
            R.drawable.icon_999
        }
    }
}
