package com.app.bookrecordapp.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.app.bookrecordapp.R
import com.app.bookrecordapp.data.AppDatabase
import com.app.bookrecordapp.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembershipScreen(navController: NavController) {

    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "contacts.db"
        )
            .addMigrations()
            .build()
    }
    val scope = rememberCoroutineScope()

    var textId by remember { mutableStateOf("") }
    var savedTextId by remember { mutableStateOf(value = "") }
    var textPw by remember { mutableStateOf("") }
    var savedTextPw by remember { mutableStateOf(value = "") }

    fun workableButton(): Boolean {
        return textId.isNotEmpty() && textPw.isNotEmpty()
    }

    Box() {

        Image(
            painter = painterResource(id = R.drawable.greendaisy),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)


        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.book_icon),
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)

            )
            Spacer(modifier = Modifier.padding(40.dp))



            TextField(
                value = textId,
                onValueChange = { textId = it },
                label = { Text("ID") }
            )
            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = textPw,
                onValueChange = { textPw = it },
                label = { Text("PASSWORD") },

                )
            Spacer(modifier = Modifier.padding(12.dp))
            Button(
                onClick = {
                    savedTextId = textId
                    savedTextPw = textPw

                    val newUser = User(

                        textId = textId,
                        textPw = textPw,
                        text="",
                        title="",
                        description = "",


                    )
                    scope.launch(Dispatchers.IO) {
                        db.userDao().insertAll(newUser)
                    }
                    navController.navigate("registration")

                },
                enabled = workableButton()
            ) {
                Text(text = "회원 등록")

            }

        }
    }

}