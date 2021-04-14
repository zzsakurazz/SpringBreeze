package zz.weather.example.ui.widget.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.theme.colorDay
import androidx.constraintlayout.compose.ConstraintLayout
import zz.weather.example.ui.widget.DividerAlpha
import zz.weather.example.ui.widget.VerticalDivider
import zz.weather.example.vm.AirNowBeanState
import zz.weather.example.vm.WeatherNowBeanState

/**
 * @author zhangzheng
 * @Date  2021/4/13 4:15 下午
 * @ClassName HomePager
 * <p>
 * Desc :
 */
@Composable
fun HomePage(weatherData: WeatherNowBeanState?, airNowData: AirNowBeanState?) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        //屏幕内容区域
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorDay
                    ),
            ) {
                weatherInfoTpoWidget(weatherData,airNowData)
            }
        },
    )
}

/**
 * 顶部数据widget
 */
@Composable
fun weatherInfoTpoWidget(weatherData: WeatherNowBeanState?, airNowData: AirNowBeanState?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(modifier = Modifier.padding(top = 140.dp)) {
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
                Box(Modifier.height(4.dp))
                MaxTemp("15.5", true)
                Box(Modifier.height(8.dp))
                MaxTemp("-23.5", false)
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
            MoreInfo(airNowData?.pm2p5?:"", "pm2.5")
            VerticalDivider(height = 35.dp, color = Color.White.copy(alpha = DividerAlpha))
            MoreInfo(
                "${weatherData?.windScale ?: "--"}级",
                if (weatherData?.windDir.isNullOrEmpty()) "风力" else weatherData?.windDir ?: ""
            )

        }

    }
}

/**
 * 更多数据widget
 */
@Composable
fun MoreInfo(data: String, note: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = data, fontSize = 16.sp, color = Color.White)
        Box(Modifier.height(5.dp))
        Text(text = note, fontSize = 12.sp, color = Color.White)
    }
}

/**
 * 最高/最低温度
 */
@Composable
private fun MaxTemp(temp: String, type: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (type) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .height(10.dp)
                .width(10.dp)
        )
        Text(
            fontSize = 12.sp,
            text = "$temp°C",
            color = Color.White,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpringBreezeTheme {
        HomePage(null, null)
    }
}

