package com.app.bookrecordapp.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("translation") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "문장 추출&번역",
                    fontSize = 20.sp
                )

            }
            Spacer(modifier = Modifier.padding(24.dp))

            Button(
                onClick = { navController.navigate("translationRecord") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "나의 책 구절",
                    fontSize = 20.sp
                )

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(
                onClick = { navController.navigate("record") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {

                Text(
                    text = "독서 기록 하기",
                    fontSize = 20.sp
                )

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(
                onClick = { navController.navigate("myBook") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "나의 독서 기록",
                    fontSize = 20.sp
                )

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(
                onClick = { navController.navigate("extraction") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "도서 항목 추출",
                    fontSize = 20.sp
                )

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(
                onClick = { navController.navigate("stopwatch") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "독서 타이머",
                    fontSize = 20.sp
                )

            }


        }


    }
}