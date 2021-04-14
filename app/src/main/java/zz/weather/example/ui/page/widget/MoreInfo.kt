package zz.weather.example.ui.page.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author zhangzheng
 * @Date  2021/4/14 6:26 下午
 * @ClassName MoreInfo
 * <p>
 * Desc :
 */

/**
 * 更多数据widget
 */
@Composable
fun MoreInfo(data: String, note: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = data, fontSize = 18.sp, color = Color.White)
        Box(Modifier.height(3.dp))
        Text(text = note, fontSize = 10.sp, color = Color.White)
    }
}