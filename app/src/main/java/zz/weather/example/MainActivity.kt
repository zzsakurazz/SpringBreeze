package zz.weather.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather
import zz.weather.example.ui.theme.SpringBreezeTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpringBreezeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        initWwatherData()
    }

    private fun initWwatherData() {

        QWeather.getWeatherNow(this, "CN101010100",object : QWeather.OnResultWeatherNowListener{
            override fun onError(error: Throwable?) {
            }

            override fun onSuccess(data: WeatherNowBean?) {
                val now=data?.now
                Log.e("zz",now?.temp?:"")
            }

        })
    }
}

@Composable
fun Greeting(temp: String) {
    Text(text = "当前气温 $temp°")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpringBreezeTheme {
        Greeting("Android")
    }
}