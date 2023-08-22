package com.app.bookrecordapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myBookRecordScreen(
    navController: NavController,
    userDao: UserDAO,
    isFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit
) {


    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "contacts.db"
    )
        .addMigrations()
        .build()

    var showDetails by remember { mutableStateOf(false) }
    var uid by remember { mutableStateOf(value = "") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(
                onClick = { showDetails = !showDetails },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "나의 기록")
            }


            OutlinedTextField(
                value = uid,
                onValueChange = { uid = it },
                Modifier
                    .height(70.dp)
                    .width(50.dp)
                    .padding(top = 24.dp)
            )

            Spacer(
                modifier = Modifier
                    .padding(8.dp)
            )


            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        val userList = db.userDao().getAll2()
                        userList.find { it.uid == uid.toIntOrNull() }?.let {
                            db.userDao().delete(it)
                        }
                    }


                },
                Modifier
                    .padding(top = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }


        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showDetails) {
            val titlesDescriptionsImages by db.userDao().getAllTitlesDescriptionsAndImageUris()
                .collectAsState(
                    initial = emptyList()
                )
//            remember { mutableStateOf(emptyList<UserDAO.TitleDescriptionImage>()) }
//            LaunchedEffect(showDetails.value) {
//                if (showDetails.value) {
//                    coroutineScope.launch {
//                        val fetchedData = withContext(Dispatchers.IO) {
//                            db.userDao().getAllTitlesDescriptionsAndImageUris().first()
//                        }
//                        titlesDescriptionsImages = fetchedData
//                    }
//                }
//            }

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
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.TopEnd,
                                    ) {
                                        IconButton(onClick = {
                                            onTabFavorite(!isFavorite)
                                        }) {
                                            Icon(
                                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                                contentDescription = "",
                                                tint = MaterialTheme.colorScheme.tertiary
                                            )

                                        }

                                    }
                                    Text(text = "no.:${data.uid}")
                                    Text(text = "title: ${data.title}")
                                    Text(
                                        text = "memo: ${data.description}",
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