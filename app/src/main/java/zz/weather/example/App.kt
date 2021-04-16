package zz.weather.example

import android.app.Application
import android.content.Context
import com.qweather.sdk.view.HeConfig
import zz.weather.example.utlis.LocationManager

/**
 * @author zhangzheng
 * @Date  2021/4/10 4:41 下午
 * @ClassName App
 * <p>
 * Desc :
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化和风SDK
        initQWeather()

        initMap()
    }

    private fun initMap() {

    }

    private fun initQWeather() {
        HeConfig.init("HE2104101620571725", "3cdfb1768b994798bbf2e715ccf9a795")
        HeConfig.switchToDevService()
    }

}