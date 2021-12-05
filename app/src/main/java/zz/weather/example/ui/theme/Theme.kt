package zz.weather.example.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import zz.weather.example.ui.view.DividerAlpha

private val DarkColorPalette = SpringBreezeColor(
    topText = white3,
    listText = black1,
    background = white2,
    divider = white.copy(alpha = DividerAlpha),
    sunny = sunnyNight,
    rain = rainNight,
    cloudy = cloudyNight,
    overcast = overcastNight,
    sleet = sleetNight,
    snow = snowNight,
    hail = hailNight,
    fog = fogNight,
    haze = hazeNight,
    sandstorm = sandstormNight,
)

private val LightColorPalette = SpringBreezeColor(
    topText = white,
    listText = black6,
    background = white1,
    divider = white.copy(alpha = DividerAlpha),
    sunny = sunnyNight,
    rain = rainLight,
    cloudy = cloudyLight,
    overcast = overcastLight,
    sleet = sleetLight,
    snow = snowLight,
    hail = hailLight,
    fog = fogLight,
    haze = hazeLight,
    sandstorm = sandstormLight,
)

private val LocalWeComposeColors = compositionLocalOf {
    LightColorPalette
}

object SpringBreezeTheme {
    val colors: SpringBreezeColor
        @Composable
        get() = LocalWeComposeColors.current

    enum class Theme {
        Light, Dark
    }
}

class SpringBreezeColor(
    topText: Color,
    listText: Color,
    background: Color,
    divider: Color,
    sunny: Color,// 晴天
    rain: Color,// 雨天
    cloudy: Color,// 多云
    overcast: Color,// 阴天
    sleet: Color,// 雨夹雪
    snow: Color,// 雪
    hail: Color,// 冰雹
    fog: Color,// 雾
    haze: Color,// 霾
    sandstorm: Color,// 沙尘暴
) {
    var topText: Color by mutableStateOf(topText)
        private set
    var listText: Color by mutableStateOf(listText)
        private set
    var background: Color by mutableStateOf(background)
        private set
    var divider: Color by mutableStateOf(divider)
        private set
    var sunny: Color by mutableStateOf(sunny)
        private set
    var rain: Color by mutableStateOf(rain)
        private set
    var cloudy: Color by mutableStateOf(cloudy)
        private set
    var overcast: Color by mutableStateOf(overcast)
        private set
    var sleet: Color by mutableStateOf(sleet)
        private set
    var snow: Color by mutableStateOf(snow)
        private set
    var hail: Color by mutableStateOf(hail)
        private set
    var fog: Color by mutableStateOf(fog)
        private set
    var haze: Color by mutableStateOf(haze)
        private set
    var sandstorm: Color by mutableStateOf(sandstorm)
        private set
}

@Composable
fun SpringBreezeTheme(
    theme: SpringBreezeTheme.Theme = SpringBreezeTheme.Theme.Light,
    content: @Composable() () -> Unit
) {
    // 获取夜间模式状态
    val targetColors = when (theme) {
        SpringBreezeTheme.Theme.Light -> LightColorPalette
        else -> DarkColorPalette
    }
    val textPrimary = animateColorAsState(targetColors.topText, TweenSpec(600))
    val textSecondary = animateColorAsState(targetColors.listText, TweenSpec(600))
    val background = animateColorAsState(targetColors.background, TweenSpec(600))
    val divider = animateColorAsState(targetColors.divider, TweenSpec(600))
    val sunny = animateColorAsState(targetColors.sunny, TweenSpec(600)) // 晴天
    val rain = animateColorAsState(targetColors.rain, TweenSpec(600)) // 雨天
    val cloudy = animateColorAsState(targetColors.cloudy, TweenSpec(600)) // 多云
    val overcast = animateColorAsState(targetColors.overcast, TweenSpec(600)) // 阴天
    val sleet = animateColorAsState(targetColors.sleet, TweenSpec(600)) // 雨夹雪
    val snow = animateColorAsState(targetColors.snow, TweenSpec(600)) // 雪
    val hail = animateColorAsState(targetColors.hail, TweenSpec(600)) // 冰雹
    val fog = animateColorAsState(targetColors.fog, TweenSpec(600)) // 雾
    val haze = animateColorAsState(targetColors.haze, TweenSpec(600)) // 霾
    val sandstorm = animateColorAsState(targetColors.sandstorm, TweenSpec(600)) // 沙尘暴
    val colors = SpringBreezeColor(
        topText = textPrimary.value,
        listText = textSecondary.value,
        background = background.value,
        divider = divider.value,
        sunny = sunny.value,
        rain = rain.value,
        cloudy = cloudy.value,
        overcast = overcast.value,
        sleet = sleet.value,
        snow = snow.value,
        hail = hail.value,
        fog = fog.value,
        haze = haze.value,
        sandstorm = sandstorm.value,
    )

    CompositionLocalProvider(LocalWeComposeColors provides colors) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}