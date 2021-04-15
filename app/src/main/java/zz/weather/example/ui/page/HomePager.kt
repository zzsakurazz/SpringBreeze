package zz.weather.example.ui.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState
import zz.weather.example.ui.page.widget.HourRowList
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.theme.colorDay
import zz.weather.example.ui.page.widget.WeatherInfoTpoWidget
import zz.weather.example.ui.page.widget.WeekColumnItem
import zz.weather.example.ui.theme.colorNight
import zz.weather.example.ui.theme.colorTextDefault
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
            //TODO 嵌套待优化
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //当前天气信息widget
                item {
                    WeatherInfoTpoWidget(weatherData, airNowData, weatherWeekData)
                }
                item {
                    HourRowList(weatherWeekData?.dailyList)
                }
                weatherWeekData?.dailyList?.let {
                    itemsIndexed(items = it) { index, item ->
                        WeekColumnItem(data = item)
                    }
                }
                item {
                    Text(
                        text = "数据来源:和风天气",
                        Modifier.padding(top = 20.dp,bottom = 20.dp),
                        fontSize = 12.sp,
                        color = colorTextDefault
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
        HomePage(null, null, null)
    }
}

