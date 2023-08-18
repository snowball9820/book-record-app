package com.app.bookrecordapp

//data class WeatherResponse(
//    val weather: List<Weather>,
//    val main: Main
//)
//
//data class Weather(
//    val main: String
//)
//
//data class Main(
//    val temp: Double
//)
//
//interface WeatherApi {
//    @GET("weather")
//    suspend fun getCurrentWeather(
//        @Query("q") cityName: String,
//        @Query("appid") apiKey: String
//    ): WeatherResponse
//}
//
//enum class BookGenre(val genreName: String) {
//    MYSTERY("Mystery"),
//    FANTASY("Fantasy"),
//    ROMANCE("Romance"),
//    ADVENTURE("Adventure"),
//    FICTION("Fiction")
//}
//
//fun recommendGenre(weatherState: String): BookGenre {
//    return when (weatherState) {
//        "Clear" -> BookGenre.MYSTERY
//        "Clouds" -> BookGenre.FANTASY
//        "Rain" -> BookGenre.ROMANCE
//        "Snow" -> BookGenre.ADVENTURE
//        else -> BookGenre.FICTION
//    }
//}
//@ExperimentalMaterial3Api
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WeatherRecommendationScreen(weatherApi: WeatherApi) {
//    var weatherState by remember { mutableStateOf("Clear") }
//    var recommendedGenre by remember { mutableStateOf(BookGenre.MYSTERY) }
//
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(true) {
//        scope.launch {
//            val weatherResponse = withContext(Dispatchers.IO) {
//                weatherApi.getCurrentWeather("Seoul", "9e35a1e52cab1d56c5e0526a5c7449e1")
//            }
//            weatherState = weatherResponse.weather[0].main
//            recommendedGenre = recommendGenre(weatherState)
//        }
//    }
//
//    Scaffold(
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = "오늘의 날씨: $weatherState")
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "추천 장르: ${recommendedGenre.genreName}")
//            }
//        }
//    )
//}
//
//@Composable
//fun App() {
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.openweathermap.org/data/2.5/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//
//    val weatherApi = retrofit.create(WeatherApi::class.java)
//
//    WeatherRecommendationScreen(weatherApi)
//}