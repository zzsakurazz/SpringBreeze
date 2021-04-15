package zz.weather.example.ui.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState
import zz.weather.example.ui.page.widget.HourRowList
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.theme.colorDay
import zz.weather.example.ui.page.widget.WeatherInfoTpoWidget
import zz.weather.example.ui.theme.colorNight
import zz.weather.example.utlis.isNight

/**
 * @author zhangzheng
 * @Date  2021/4/13 4:15 下午
 * @ClassName HomePager
 * <p>
 * Desc :
 */
@Composable
fun HomePage(
    weatherData: WeatherNowBeanState?,
    airNowData: AirNowBeanState?,
    weatherWeekData: WeatherWeekState?
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    Scaffold(
        scaffoldState = scaffoldState,
        //屏幕内容区域
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                //当前天气信息widget
                WeatherInfoTpoWidget(weatherData, airNowData, weatherWeekData)
                Column {
                    HourRowList(weatherWeekData?.dailyList)
                }
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpringBreezeTheme {
        HomePage(null, null, null)
    }
}

