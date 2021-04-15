package zz.weather.example.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zz.weather.example.ui.theme.SpringBreezeTheme


/**
 * 最高/最低温度
 */
@Composable
fun MaxTemp(temp: String, type: Boolean) {
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
fun MaxTempPreview() {
    SpringBreezeTheme { MaxTemp("12",false)}
}
