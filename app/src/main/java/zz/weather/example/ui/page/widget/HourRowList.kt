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
import com.qweather.sdk.bean.weather.WeatherDailyBean
import zz.weather.example.R
import zz.weather.example.ui.theme.SpringBreezeTheme

/**
 * @author zhangzheng
 * @Date  2021/4/15 11:03 上午
 * @ClassName HourRowList
 * <p>
 * Desc :
 */

@Composable
fun HourRowList(datas: List<WeatherDailyBean.DailyBean>?) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 23.dp, vertical = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        datas?.let {
            itemsIndexed(items = it) { _, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "12°C", fontSize = 14.sp, fontWeight = FontWeight.W400)
                    Box(Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icon_103),
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                    Box(Modifier.height(10.dp))
                    Text(text = "12:00", fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourRowListPreview() {
    SpringBreezeTheme { HourRowList(null) }
}
