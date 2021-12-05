package zz.weather.example.ui.page.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import kotlinx.coroutines.launch
import zz.weather.example.ui.theme.SpringBreezeTheme
import com.google.accompanist.insets.ui.TopAppBar

/**
 * @author zhangzheng
 * @Date  2021/12/5 7:04 下午
 * @ClassName HomeTopBar
 * <p>
 * Desc : Google的AppBar为啥永远都不支持title居中？？
 *        TODO 后期替换为自定义的TopAppBar，确保title居中
 */
@Composable
fun HomeTopBar(city: String?, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(
                text = city ?: "城市",
                color = SpringBreezeTheme.colors.topText,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp),
                    imageVector = Icons.Filled.Menu,
                    tint = SpringBreezeTheme.colors.topText,
                    contentDescription = "侧边栏"
                )
            }
        },
        backgroundColor = SpringBreezeTheme.colors.sunny,
        elevation = 0.dp,
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}