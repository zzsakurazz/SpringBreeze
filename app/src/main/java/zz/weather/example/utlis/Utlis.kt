package zz.weather.example.utlis

import android.content.Context
import android.util.Log
import java.security.AccessControlContext
import java.text.SimpleDateFormat
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

fun isNight():Boolean {
    val hour = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now())
    Log.e("zz","当前是:${hour}")
    return (hour.toInt() in 0..5) || (hour.toInt() in 18..23)
}

fun getStatusBarHeight(context: Context):Int{
    val resources=context.resources
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}