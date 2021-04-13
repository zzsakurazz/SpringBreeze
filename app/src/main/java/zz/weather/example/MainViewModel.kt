package zz.weather.example

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather

/**
 * @author zhangzheng
 * @Date  2021/4/12 6:42 下午
 * @ClassName MainViewModel
 * <p>
 * Desc :
 */
class MainViewModel (private val app: Application): AndroidViewModel(app){


    //获取当前天气
      fun initWatherData() {
        QWeather.getWeatherNow(app, "CN101010100", object : QWeather.OnResultWeatherNowListener {
            override fun onError(error: Throwable?) {
            }

            override fun onSuccess(data: WeatherNowBean?) {
                val now = data?.now
                Log.e("zz", now?.temp ?: "")
            }

        })
    }
}