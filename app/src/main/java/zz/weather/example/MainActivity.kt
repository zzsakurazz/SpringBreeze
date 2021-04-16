package zz.weather.example

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.google.accompanist.insets.ProvideWindowInsets
import pub.devrel.easypermissions.AfterPermissionGranted
import zz.weather.example.ui.view.home.HomePage
import zz.weather.example.ui.theme.SpringBreezeTheme
import zz.weather.example.vm.MainViewModel
import pub.devrel.easypermissions.EasyPermissions
import zz.weather.example.PermissionConfig.RC_CAMERA_AND_LOCATION
import zz.weather.example.utlis.LocationManager


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var mLocationClient: AMapLocationClient? = null
    private val mLocationOption = AMapLocationClientOption()
    private var option = AMapLocationClientOption()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        LocationManager.initManager(this)
    }

    private fun initLocation() {
        mLocationClient = AMapLocationClient(this)
        //定位场景
        option.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.Transport
        mLocationClient?.setLocationOption(option)
        //定位精度
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        ///定位一次
        mLocationClient?.setLocationOption(mLocationOption)
        mLocationClient?.startLocation()
        mLocationClient?.setLocationListener { location ->
            if(location.errorCode==0){
                viewModel.refreshWatherData(this,location)
            }else{
              // todo 失败处理
            }
            mLocationClient?.stopLocation()
        }

    }

    //处理权限
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun initView() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val weatherData by viewModel.weatherData.observeAsState()
            val airNowData by viewModel.airNowData.observeAsState()
            val weatherWeekData by viewModel.weatherWeekData.observeAsState()
            val weather24HourlyData by viewModel.weather24HourlyData.observeAsState()
            val city by viewModel.city.observeAsState()
            SpringBreezeTheme {
                ProvideWindowInsets {
                    HomePage(city,weatherData, airNowData,weather24HourlyData ,weatherWeekData)
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
            initLocation()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请提供一下这些权限吧，秋梨膏", RC_CAMERA_AND_LOCATION,*perms)
        }

    }

}



