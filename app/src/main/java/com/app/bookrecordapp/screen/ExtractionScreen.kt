import androidx.compose.runtime.Composable
import androidx.navigation.NavController

//package com.app.bookrecordapp.screen
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
@Composable
fun ExtractionScreen(navController: NavController) {

//    var city by remember { mutableStateOf(TextFieldValue("Seoul")) }
//    var apiKey by remember { mutableStateOf(TextFieldValue("(9e35a1e52cab1d56c5e0526a5c7449e1)")) }
//    var weatherDescription by remember { mutableStateOf("") }
//    var recommendedGenre by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(
//            value = city.text,
//            onValueChange = { city = it.toTextFieldValue() },
//            label = { Text("City") }
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = apiKey.text,
//            onValueChange = { apiKey = it.toTextFieldValue() },
//            label = { Text("API Key") }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                fetchWeatherAndRecommendGenre(city.text, apiKey.text)
//            }
//        ) {
//            Text("Get Weather and Genre Recommendation")
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("Weather: $weatherDescription")
//        Text("Recommended Genre: $recommendedGenre")
//    }
}
//
//@Composable
//fun fetchWeatherAndRecommendGenre(city: String, apiKey: String) {
//    val lang = "kr"
//
//    val api = "http://api.openweathermap.org/data/2.5/" +
//            "weather?q=$city&appid=$apiKey&lang=$lang&units=metric"
//
//    val result = URL(api).readText()
//
//    val parser: Parser = Parser.default()
//    val json: JsonObject = parser.parse(StringBuilder(result)) as JsonObject
//
//    val weatherArray = json.array<JsonObject>("weather")
//    if (weatherArray != null && weatherArray.isNotEmpty()) {
//        val weather = weatherArray[0]
//        val weatherDescription = weather.string("main")
//        val recommendedGenre = recommendGenre(weatherDescription ?: "")
//
//        weatherDescription?.let {
//            weatherDescription.value = it
//        }
//        recommendedGenre?.let {
//            recommendedGenre.value = it
//        }
//    } else {
//        weatherDescription = ""
//        recommendedGenre = ""
//    }
//}
//
//@Composable
//fun recommendGenre(weatherDescription: String): String {
//    return when (weatherDescription) {
//        "Clear" -> "Developer Books"
//        "Clouds" -> "Classic Literature"
//        "Rain" -> "Mystery Novels"
//        else -> "General Fiction"
//    }
//}

