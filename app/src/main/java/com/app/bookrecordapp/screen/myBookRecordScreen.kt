package com.app.bookrecordapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.rememberImagePainter
import com.app.bookrecordapp.UriTypeConverter
import com.app.bookrecordapp.data.AppDatabase
import com.app.bookrecordapp.data.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun myBookRecordScreen(navController: NavController, userDao: UserDAO) {

    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "contacts.db"
    )
        .addMigrations()
        .build()


    var titlesDescriptionsImages by remember { mutableStateOf(emptyList<UserDAO.TitleDescriptionImage>()) }
    var showDetails = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(showDetails.value) {
        if (showDetails.value) {
            coroutineScope.launch {
                val fetchedData = withContext(Dispatchers.IO) {
                    db.userDao().getAllTitlesDescriptionsAndImageUris()
                }
                titlesDescriptionsImages = fetchedData
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showDetails.value = !showDetails.value },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "나의 기록")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showDetails.value) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(titlesDescriptionsImages) { data ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 12.dp
                        )


                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row {
                                data.selectedImageUri?.let { imageUriString ->
                                    val imageUri = UriTypeConverter().toUri(imageUriString)
                                    imageUri?.let { uri ->
                                        Image(
                                            painter = rememberImagePainter(uri),
                                            contentDescription = null,
                                            modifier = Modifier.size(100.dp)
                                        )
                                    }
                                }
                                Column {
                                    Text(text = "제목: ${data.title}")
                                    Text(
                                        text = "메모: ${data.description}",
                                        style = LocalTextStyle.current.merge(
                                            TextStyle(
                                                lineHeight = 1.5.em,
                                                platformStyle = PlatformTextStyle(
                                                    includeFontPadding = false
                                                ),
                                                lineHeightStyle = LineHeightStyle(
                                                    alignment = LineHeightStyle.Alignment.Center,
                                                    trim = LineHeightStyle.Trim.None
                                                )
                                            )
                                        ),
                                    )

                                }


                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }

    }
}