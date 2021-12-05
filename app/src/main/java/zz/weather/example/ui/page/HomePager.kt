package zz.weather.example.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.Weather24HourlyState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState
import zz.weather.example.ui.page.widget.*
import zz.weather.example.ui.theme.SpringBreezeTheme

/**
 * @author zhangzheng
 * @Date  2021/4/13 4:15 下午
 * @ClassName HomePager
 * <p>
 * Desc :
 */
@Composable
fun HomePage(
    city: String?,
    weatherData: WeatherNowBeanState?,
    airNowData: AirNowBeanState?,
    weather24HourlyData: Weather24HourlyState?,
    weatherWeekData: WeatherWeekState?
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        // 脚手架的状态
        scaffoldState = scaffoldState,
        // 顶部区域
        topBar = {
            HomeTopBar(city, scaffoldState)
        },
        drawerContent = {
            HomeDrawerContent()
        },
        // 屏幕内容区域
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = SpringBreezeTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 当前天气信息widget
                item {
                    WeatherInfoTpoWidget(weatherData, airNowData, weatherWeekData)
                }
                // 当前24小时横向列表
                item {
                    HourRowList(weather24HourlyData?.hourlyList)
                }
                // 未来一周的竖向列表
                itemsIndexed(weatherWeekData?.dailyList ?: arrayListOf()) { _, item ->
                    WeekColumnItem(data = item)
                }
                // 天气来源说明
                item {
                    Text(
                        text = "数据来源:和风天气",
                        Modifier.padding(top = 20.dp, bottom = 20.dp),
                        fontSize = 12.sp,
                        color = SpringBreezeTheme.colors.listText
                    )
                }
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpringBreezeTheme {
    }
}

