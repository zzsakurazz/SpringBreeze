package zz.weather.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import zz.weather.example.ui.view.home.HomePage
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.vm.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val weatherData by viewModel.weatherData.observeAsState()
            val airNowData by viewModel.airNowData.observeAsState()
            val weatherWeekData by viewModel.weatherWeekData.observeAsState()
            SpringBreezeTheme {
                ProvideWindowInsets {
                    HomePage(weatherData,airNowData,weatherWeekData)
                }
            }
        }
    }

    private fun initData() {
        viewModel.refreshWatherData(this)
    }

}


