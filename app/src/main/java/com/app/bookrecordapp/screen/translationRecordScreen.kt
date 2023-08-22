package com.app.bookrecordapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.app.bookrecordapp.data.AppDatabase
import com.app.bookrecordapp.data.User
import com.app.bookrecordapp.data.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun translationRecordScreen(navController: NavController,
                            userDao: UserDAO,
                            isFavorite:Boolean,
                            onTabFavorite:(Boolean)->Unit)
{


    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "contacts.db"
    ).build()

    val userDao: UserDAO = db.userDao()
    val textState = remember { mutableStateOf<List<String>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    fun fetchText() {
        try {
            coroutineScope.launch(Dispatchers.IO) {
                val allUsers: List<User> = userDao.getAll()
                val allText = allUsers.map { it.text }
                textState.value = allText
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { fetchText() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "나의 책 구절 확인")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val textIds = textState.value
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(textIds) { text ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = MaterialTheme.shapes.medium,

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp

                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopEnd,
                    ){
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
                    Text(
                        text = text,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}