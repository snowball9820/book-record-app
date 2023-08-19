package com.app.bookrecordapp.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {


            Row {

                MenuButton(
                    onClick = { navController.navigate("translation") },
                    text = "문장 추출&번역",


                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("translationRecord") },
                    text = "나의 책 구절",

                )

            }

            Row {
                MenuButton(
                    onClick = { navController.navigate("record") },
                    text = "독서 기록 하기"
                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("myBook") },
                    text = "나의 독서 기록"
                )

            }
            Row {
                MenuButton(
                    onClick = { navController.navigate("extraction") },
                    text = "도서 항목 추출"
                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("stopwatch") },
                    text = "독서 타이머"

                )

            }

        }
    }
}

@Composable
fun MenuButton(
    onClick: () -> Unit,
    text: String
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.secondary

        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

