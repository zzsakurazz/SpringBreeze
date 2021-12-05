package zz.weather.example.ui.page.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import zz.weather.example.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import zz.weather.example.bean.AirNowBeanState
import zz.weather.example.bean.WeatherNowBeanState
import zz.weather.example.bean.WeatherWeekState
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.view.VerticalDivider

/**
 * 顶部数据widget
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherInfoTpoWidget(
    weatherData: WeatherNowBeanState?,
    airNowData: AirNowBeanState?,
    weatherWeekData: WeatherWeekState?
) {
    var state by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        state = true
        delay(1200)
    }
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)
            .background(
                color = SpringBreezeTheme.colors.sunny
            ),
    ) {
        AnimatedVisibility(
            visible = state,
            enter = slideInHorizontally(
                initialOffsetX = { -1800 },
                animationSpec = tween(durationMillis = 2500)
            ),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 70.dp, start = 80.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cloud),
                contentDescription = "云彩",
                modifier = Modifier
                    .size(60.dp)
                    .offset(x = 0.dp, y = offset.dp)
            )
        }
        AnimatedVisibility(
            visible = state,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 2000)
            ),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(start = 160.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rain_ground),
                contentDescription = "小房子",

                )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstraintLayout(modifier = Modifier.padding(top = 100.dp)) {
                val (temp, info) = createRefs()
                Text(
                    fontSize = 84.sp,
                    text = "${weatherData ?.temp ?: "--"}°",
                    color = SpringBreezeTheme.colors.topText,
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
                        text = weatherData ?.text ?: "未知",
                        color = SpringBreezeTheme.colors.topText,
                    )
                    Spacer(Modifier.height(4.dp))
                    MaxTemp(weatherWeekData ?.dailyList?.get(0)?.tempMax ?: "--", true)
                    Spacer(Modifier.height(8.dp))
                    MaxTemp(weatherWeekData ?.dailyList?.get(0)?.tempMin ?: "--", false)
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoreInfo("${weatherData ?.humidity ?: "--"}%", "湿度")
                VerticalDivider(height = 35.dp, color = SpringBreezeTheme.colors.divider)
                MoreInfo(airNowData ?.pm2p5 ?: "--", "pm2.5")
                VerticalDivider(height = 35.dp, color = SpringBreezeTheme.colors.divider)
                MoreInfo(
                    "${weatherData ?.windScale ?: "--"}级",
                    if (weatherData ?.windDir.isNullOrEmpty()) "风力" else weatherData ?.windDir
                        ?: ""
                )

            }

        }
    }

}
