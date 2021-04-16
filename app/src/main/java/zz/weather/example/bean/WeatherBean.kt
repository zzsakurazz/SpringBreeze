package zz.weather.example.bean

import androidx.compose.runtime.Immutable
import com.qweather.sdk.bean.weather.WeatherDailyBean
import com.qweather.sdk.bean.weather.WeatherHourlyBean

/**
 * @author zhangzheng
 * @Date  2021/4/14 7:33 下午
 * @ClassName WeatherBena
 * <p>
 * Desc :
 */

@Immutable
data class WeatherNowBeanState(
    val temp: String,//实时温度
    val text: String,//天气
    val Icon: String,//天气code
    val windDir: String,//风向
    val windScale: String,//风速
    val humidity: String,//湿度
)

@Immutable
data class AirNowBeanState(
    val pm2p5: String,//实时空气质量
)

@Immutable
data class Weather24HourlyState(
    val hourlyList: List<WeatherHourlyBean.HourlyBean>,//24小时数据
)

@Immutable
data class WeatherWeekState(
    val dailyList: List<WeatherDailyBean.DailyBean>,//一周数据
)