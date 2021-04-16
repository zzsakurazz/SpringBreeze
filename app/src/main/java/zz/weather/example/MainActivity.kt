package zz.weather.example

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import pub.devrel.easypermissions.AfterPermissionGranted
import zz.weather.example.ui.view.home.HomePage
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.vm.MainViewModel
import pub.devrel.easypermissions.EasyPermissions
import zz.weather.example.PermissionConfig.RC_CAMERA_AND_LOCATION


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }

    //处理权限
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private fun initView() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val weatherData by viewModel.weatherData.observeAsState()
            val airNowData by viewModel.airNowData.observeAsState()
            val weatherWeekData by viewModel.weatherWeekData.observeAsState()
            SpringBreezeTheme {
                ProvideWindowInsets {
                    HomePage(weatherData, airNowData, weatherWeekData)
                }
            }
        }
    }

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private fun initData() {
        val perms = arrayOf(
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.FOREGROUND_SERVICE,
        )
        if (EasyPermissions.hasPermissions(this,*perms)) {
            viewModel.refreshWatherData(this)
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请提供一下这些权限吧，秋梨膏", RC_CAMERA_AND_LOCATION,*perms)
        }

    }

}



