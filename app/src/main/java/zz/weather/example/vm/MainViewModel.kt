package zz.weather.example.vm

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qweather.sdk.bean.air.AirNowBean
import com.qweather.sdk.bean.base.Lang
import com.qweather.sdk.bean.weather.WeatherDailyBean
import com.qweather.sdk.bean.weather.WeatherHourlyBean
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.Weather24HourlyState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState

/**
 * @author zhangzheng
 * @Date  2021/4/12 6:42 下午
 * @ClassName MainViewModel
 * <p>
 * Desc :
 */
class MainViewModel : ViewModel() {


    //实时天气数据
    private val _weatherData = MutableLiveData<WeatherNowBeanState>()
    val weatherData: LiveData<WeatherNowBeanState> = _weatherData

    //实时空气质量
    private val _airNowData = MutableLiveData<AirNowBeanState>()
    val airNowData: LiveData<AirNowBeanState> = _airNowData

    //24小时天气数据
    private val _weather24HourlyData = MutableLiveData<Weather24HourlyState>()
    val weather24HourlyData: LiveData<Weather24HourlyState> = _weather24HourlyData

    //一周天气数据
    private val _weatherWeekData = MutableLiveData<WeatherWeekState>()
    val weatherWeekData: LiveData<WeatherWeekState> = _weatherWeekData

    private val _location = MutableLiveData("116.41,39.92")

    fun refreshWatherData(context: Context) {
        resultWeatherNowData(context)
        resultAirNow(context)
        resultWeather24Hourly(context)
        resultWeatherWeek(context)
    }

    private fun resultWeatherWeek(context: Context) {
        QWeather.getWeather7D(
            context,
            _location.value,
            object : QWeather.OnResultWeatherDailyListener {
                override fun onError(error: Throwable?) {
                    error?.printStackTrace()
                }

                override fun onSuccess(data: WeatherDailyBean?) {
                    data?.let {
                        _weatherWeekData.value = WeatherWeekState(it.daily)
                    }

                }
            })
    }

    private fun resultWeather24Hourly(context: Context) {
        QWeather.getWeather72Hourly(context, _location.value,
            object : QWeather.OnResultWeatherHourlyListener {
                override fun onError(error: Throwable?) {
                    error?.printStackTrace()
                }

                override fun onSuccess(data: WeatherHourlyBean?) {
                    data?.let {
                        _weather24HourlyData.value = Weather24HourlyState(data.hourly)
                    }
                }
            })

    }

    private fun resultAirNow(context: Context) {
        QWeather.getAirNow(
            context,
            _location.value,
            Lang.ZH_HANS,
            object : QWeather.OnResultAirNowListener {
                override fun onError(error: Throwable?) {
                    error?.printStackTrace()
                }

                override fun onSuccess(data: AirNowBean?) {
                    data?.let {
                        _airNowData.value = AirNowBeanState(data.now.pm2p5)
                    }
                }

            })
    }

    //获取当前天气数据
    private fun resultWeatherNowData(context: Context) {
        QWeather.getWeatherNow(
            context,
            _location.value,
            object : QWeather.OnResultWeatherNowListener {
                override fun onError(error: Throwable?) {
                    error?.printStackTrace()
                }

                override fun onSuccess(data: WeatherNowBean?) {
                    data?.let {
                        _weatherData.value = WeatherNowBeanState(
                            it.now.temp,
                            it.now.text,
                            it.now.icon,
                            it.now.windDir,
                            it.now.windScale,
                            it.now.humidity,
                        )
                    }
                }

            })
    }
}
