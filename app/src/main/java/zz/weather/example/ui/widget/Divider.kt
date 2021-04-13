package zz.weather.example.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author zhangzheng
 * @Date  2021/4/13 6:00 下午
 * @ClassName Divider
 * <p>
 * Desc :
 */
@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    height: Dp = 0.dp
) {
    Box(
        modifier
            .width(thickness)
            .height(height)
            .background(color = color)
    )
}

const val DividerAlpha = 0.86f
