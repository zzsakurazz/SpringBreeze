package zz.weather.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import zz.weather.example.ui.widget.home.HomePage
import zz.weather.example.ui.theme.SpringBreezeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SpringBreezeTheme {
                ProvideWindowInsets {
                    HomePage()
                }
            }
        }
    }
}


