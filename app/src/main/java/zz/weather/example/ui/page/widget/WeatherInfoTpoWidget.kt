package zz.weather.example.ui.page.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.theme.colorDay
import zz.weather.example.ui.theme.colorNight
import zz.weather.example.ui.view.DividerAlpha
import zz.weather.example.ui.view.VerticalDivider
import zz.weather.example.utlis.getStatusBarHeight
import zz.weather.example.utlis.isNight

/**
 * 顶部数据widget
 */
@Composable
fun WeatherInfoTpoWidget(
    city: String?,
    weatherData: WeatherNowBeanState?,
    airNowData: AirNowBeanState?,
    weatherWeekData: WeatherWeekState?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(720.dp)
            .background(
                color = if (isNight()) colorNight else colorDay
            ),
    ) {
        Row(
            Modifier
                .height(60.dp)//todo 处理顶部状态栏高度问题
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = city?:"城市", color = Color.White, fontSize = 16.sp)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstraintLayout(modifier = Modifier.padding(top = 170.dp)) {
                val (temp, info) = createRefs()
                Text(
                    fontSize = 84.sp,
                    text = "${weatherData?.temp ?: "--"}°",
                    color = Color.White,
                    modifier = Modifier.constrainAs(temp) {
                        start.linkTo(parent.start)
                    }
                )
                Column(
                    modifier = Modifier
                        .width(65.dp)
                        .constrainAs(info) {
                            top.linkTo(temp.top)
                            bottom.linkTo(temp.bottom)
                            start.linkTo(temp.end)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 20.sp,
                        text = weatherData?.text ?: "未知",
                        color = Color.White,
                    )
                    Spacer(Modifier.height(4.dp))
                    MaxTemp(weatherWeekData?.dailyList?.get(0)?.tempMax ?: "--", true)
                    Spacer(Modifier.height(8.dp))
                    MaxTemp(weatherWeekData?.dailyList?.get(0)?.tempMin ?: "--", false)
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoreInfo("${weatherData?.humidity ?: "--"}%", "湿度")
                VerticalDivider(height = 35.dp, color = Color.White.copy(alpha = DividerAlpha))
                MoreInfo(airNowData?.pm2p5 ?: "--", "pm2.5")
                VerticalDivider(height = 35.dp, color = Color.White.copy(alpha = DividerAlpha))
                MoreInfo(
                    "${weatherData?.windScale ?: "--"}级",
                    if (weatherData?.windDir.isNullOrEmpty()) "风力" else weatherData?.windDir ?: ""
                )

            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun WeatherInfoTpoWidgetPreview() {
    SpringBreezeTheme { WeatherInfoTpoWidget("北京",null, null, null) }
}