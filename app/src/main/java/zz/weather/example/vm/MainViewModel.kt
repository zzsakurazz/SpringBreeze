package zz.weather.example.vm

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qweather.sdk.bean.air.AirNowBean
import com.qweather.sdk.bean.base.Lang
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather

/**
 * @author zhangzheng
 * @Date  2021/4/12 6:42 下午
 * @ClassName MainViewModel
 * <p>
 * Desc :
 */
class MainViewModel : ViewModel() {


    private val _weatherData = MutableLiveData<WeatherNowBeanState>()
    val weatherData: LiveData<WeatherNowBeanState> = _weatherData


    private val _airNowData = MutableLiveData<AirNowBeanState>()
    val airNowData: LiveData<AirNowBeanState> = _airNowData


    fun refreshWatherData(context: Context) {
        resultWeatherNowData(context)
        resultAirNow(context)
    }

    fun resultAirNow(context: Context) {
        QWeather.getAirNow(context,"116.41,39.92",Lang.ZH_HANS,object : QWeather.OnResultAirNowListener {
            override fun onError(error: Throwable?) {
                Log.e("zz","啥玩意？${error?.printStackTrace()}")

            }

            override fun onSuccess(data: AirNowBean?) {
                Log.e("zz","啥玩!？${data?.code?.code}+${data?.code?.txt}")
                data?.let {
                    _airNowData.value=AirNowBeanState(data.now.pm2p5)
                }
            }

        })
    }

    //获取当前天气数据
    fun resultWeatherNowData(context: Context) {
        QWeather.getWeatherNow(
            context,
            "CN101010100",
            object : QWeather.OnResultWeatherNowListener {
                override fun onError(error: Throwable?) {
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
    val pm2p5: String,//实时温度
)