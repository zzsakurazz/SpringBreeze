package zz.weather.example.ui.page.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qweather.sdk.bean.weather.WeatherDailyBean
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.utlis.codeToIcon
import zz.weather.example.utlis.dateToWeek

@Composable
fun WeekColumnItem(data: WeatherDailyBean.DailyBean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 23.dp, vertical = 16.dp),
    ) {
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.CenterStart) {
            Text(text = dateToWeek("yyyy-MM-dd", data.fxDate), color = SpringBreezeTheme.colors.listText)
        }
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = codeToIcon(data.iconDay.toInt())),
                contentDescription = null,
                Modifier.size(20.dp)
            )
        }
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.CenterEnd) {
            Text(text = "${data.tempMax} / ${data.tempMin}", color =  SpringBreezeTheme.colors.listText)
        }
    }

}