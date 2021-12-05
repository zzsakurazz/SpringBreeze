package zz.weather.example.ui.page.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import zz.weather.example.ui.theme.SpringBreezeTheme

/**
 * @author zhangzheng
 * @Date  2021/12/5 9:34 下午
 * @ClassName HmoeDrawerContent
 * <p>
 * Desc :
 */
@Composable
fun HomeDrawerContent() {
    Box(
        modifier = Modifier
            .background(color = SpringBreezeTheme.colors.sunny)
            .fillMaxWidth()
            .height(
                rememberInsetsPaddingValues(
                    LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ).calculateTopPadding()
            )
    ) {
        //todo 添加抽屉布局的一些内容（还没想好布局有哪些）
    }
}
