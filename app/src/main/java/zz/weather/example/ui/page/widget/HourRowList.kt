package zz.weather.example.ui.page.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qweather.sdk.bean.weather.WeatherHourlyBean
import zz.weather.example.R
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.ui.theme.colorTextDefault
import zz.weather.example.utlis.UTC_TIME
import zz.weather.example.utlis.formatDate

@Composable
fun HourRowList(datas: List<WeatherHourlyBean.HourlyBean>?) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 23.dp, vertical = 23.dp),//边距距离
        horizontalArrangement = Arrangement.spacedBy(38.dp),//内部item间距
    ) {
        datas?.let {
            itemsIndexed(items = it) { _, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${item.temp}°C",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = colorTextDefault
                    )
                    Spacer(Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icon_103),
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = processTime(formatDate(UTC_TIME, "HH", item.fxTime)),
                        fontSize = 12.sp,
                        color = colorTextDefault
                    )
                }
            }
        }
    }
}

fun processTime(time: String): String {
    return "${time}:00"
}

@Preview(showBackground = true)
@Composable
fun HourRowListPreview() {
    SpringBreezeTheme { HourRowList(null) }
}
