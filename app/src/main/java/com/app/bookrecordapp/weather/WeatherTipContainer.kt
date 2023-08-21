//package com.app.bookrecordapp.weather
//
//import com.app.bookrecordapp.weather.StartFragment.Companion.MIN_DISTANCE
//import com.app.bookrecordapp.weather.StartFragment.Companion.MIN_TIME
//import com.app.bookrecordapp.weather.StartFragment.Companion.WEATHER_REQUEST
//import com.app.bookrecordapp.weather.StartFragment.Companion.WEATHER_URL
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.location.Location
//import android.location.LocationListener
//import android.location.LocationManager
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.JsonHttpResponseHandler
//import com.loopj.android.http.RequestParams
//import cz.msebera.android.httpclient.Header
//import org.json.JSONException
//import org.json.JSONObject
//
//class StartFragment : Fragment() {
//
//    companion object {
//        const val API_KEY: String = "9e35a1e52cab1d56c5e0526a5c7449e1"
//        const val WEATHER_URL: String = "https://api.openweathermap.org/data/2.5/weather"
//        const val MIN_TIME: Long = 5000
//        const val MIN_DISTANCE: Float = 1000F
//        const val WEATHER_REQUEST: Int = 102
//    }
//
//    private lateinit var mLocationManager: LocationManager
//    private lateinit var mLocationListener: LocationListener
//    private var binding: FragmentStartBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
//        fragmentBinding.apply {
//            temperature = temperatureTv
//            weatherState = weatherTv
//            weatherTip = weatherTipTv
//            weatherIcon = weatherIc
//            binding = fragmentBinding
//        }
//        return fragmentBinding.root
//    }
//
//    override fun onResume() {
//        super.onResume()
//        getWeatherInCurrentLocation()
//    }
//
//    private fun getWeatherInCurrentLocation() {
//        mLocationManager =
//            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        mLocationListener = LocationListener { location ->
//            val params = RequestParams().apply {
//                put("lat", location.latitude)
//                put("lon", location.longitude)
//                put("appid", Companion.API_KEY)
//            }
//            doNetworking(params)
//        }
//
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
//                WEATHER_REQUEST
//            )
//            return
//        }
//        mLocationManager.requestLocationUpdates(
//            LocationManager.NETWORK_PROVIDER,
//            MIN_TIME,
//            MIN_DISTANCE,
//            mLocationListener
//        )
//        mLocationManager.requestLocationUpdates(
//            LocationManager.GPS_PROVIDER,
//            MIN_TIME,
//            MIN_DISTANCE,
//            mLocationListener
//        )
//    }
//
//    private fun doNetworking(params: RequestParams) {
//        val client = AsyncHttpClient()
//
//        client.get(WEATHER_URL, params, object : JsonHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                response: JSONObject?
//            ) {
//                val weatherData = WeatherData.fromJson(response)
//                if (weatherData != null) {
//                    updateWeather(weatherData)
//                }
//            }
//        })
//    }
//
//    private fun updateWeather(weather: WeatherData) {
//        binding?.temperatureTv?.text = "${weather.tempString} ℃"
//        binding?.weatherTv?.text = weather.weatherType
//        val resourceID = resources.getIdentifier(weather.icon, "drawable", activity?.packageName)
//        binding?.weatherIc?.setImageResource(resourceID)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        if (::mLocationManager.isInitialized) {
//            mLocationManager.removeUpdates(mLocationListener)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WeatherTipContainer() {
//
//    val binding = FragmentStartBinding.bind(View(requireContext()))
//
//    var temperature by remember { mutableStateOf("00") }
//    var weather by remember { mutableStateOf("Sunny") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//
//            ) {
//            Row(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.rabbit_cat),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(48.dp)
//                        .clip(MaterialTheme.shapes.medium)
//                        .background(MaterialTheme.colorScheme.secondary)
//                )
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    TextField(
//                        value = temperature,
//                        onValueChange = { temperature = it },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = androidx.compose.ui.text.input.KeyboardType.Number,
//                            imeAction = ImeAction.Done
//                        ),
//                        textStyle = LocalTextStyle.current.copy(fontSize = 25.sp),
//                        singleLine = true,
//                        label = { Text(text = "Temperature") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    TextField(
//                        value = weather,
//                        onValueChange = { weather = it },
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            imeAction = ImeAction.Done
//                        ),
//                        singleLine = true,
//                        label = { Text(text = "Weather") },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun getCurrentLocation(activity: ComponentActivity, onLocationReceived: (Location) -> Unit) {
//    val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    val locationListener = object : LocationListener {
//        override fun onLocationChanged(location: Location) {
//            onLocationReceived(location)
//            locationManager.removeUpdates(this)
//        }
//
//        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
//        override fun onProviderEnabled(provider: String?) {}
//        override fun onProviderDisabled(provider: String?) {}
//    }
//
//    if (ContextCompat.checkSelfPermission(
//            activity,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    ) {
//        locationManager.requestLocationUpdates(
//            LocationManager.NETWORK_PROVIDER,
//            MIN_TIME,
//            MIN_DISTANCE,
//            locationListener
//        )
//        locationManager.requestLocationUpdates(
//            LocationManager.GPS_PROVIDER,
//            MIN_TIME,
//            MIN_DISTANCE,
//            locationListener
//        )
//    } else {
//        ActivityCompat.requestPermissions(
//            activity,
//            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//            WEATHER_REQUEST
//        )
//    }
//}
//
//private fun doNetworking(params: RequestParams, onSuccess: (WeatherData) -> Unit) {
//    val client = AsyncHttpClient()
//
//    client.get(WEATHER_URL, params, object : JsonHttpResponseHandler() {
//        override fun onSuccess(
//            statusCode: Int,
//            headers: Array<out Header>?,
//            response: JSONObject?
//        ) {
//            val weatherData = WeatherData.fromJson(response)
//            if (weatherData != null) {
//                onSuccess(weatherData)
//            }
//        }
//    })
//}
//
//data class WeatherData(
//    var weatherId: Int,
//    var weatherType: String,
//    var icon: String,
//    var tempString: String,
//    var tempInt: Int
//)
//
//@Composable
//fun WeatherData.fromJson(jsonObject: JSONObject?): WeatherData? {
//    try {
//        val weatherData = WeatherData()
//        weatherData.weatherId =
//            jsonObject?.getJSONArray("weather")?.getJSONObject(0)?.getInt("id")!!
//        weatherData.weatherType =
//            jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
//        weatherData.icon = updateWeatherIcon(weatherData.weatherId)
//        val roundedTemp: Int = (jsonObject.getJSONObject("main").getDouble("temp") - 273.15).toInt()
//        weatherData.tempString = roundedTemp.toString()
//        weatherData.tempInt = roundedTemp
//        return weatherData
//    } catch (e: JSONException) {
//        e.printStackTrace()
//        return null
//    }
//}
//
//fun updateWeatherIcon(condition: Int): String {
//    return when {
//        condition in 200..299 -> "thunderstorm"
//        condition in 300..499 -> "lightrain"
//        condition in 500..599 -> "rain"
//        condition in 600..700 -> "snow"
//        condition in 701..771 -> "fog"
//        condition in 772..799 -> "overcast"
//        condition == 800 -> "clear"
//        condition in 801..804 -> "cloudy"
//        condition in 900..902 -> "thunderstorm"
//        condition == 903 -> "snow"
//        condition == 904 -> "clear"
//        condition in 905..1000 -> "thunderstorm"
//        else -> "dunno"
//    }
//}
