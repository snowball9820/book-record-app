package com.app.bookrecordapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.bookrecordapp.R

@Composable
fun MenuScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.bookreport03_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 150.dp, height = 80.dp)


            )


            Row {

                MenuButton(
                    onClick = { navController.navigate("translation") },
                    text = "문장 추출\n&번역",
                    imageResource = R.drawable.translation_icon


                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("translationRecord") },
                    text = "나의 책\n구절",
                    imageResource = R.drawable.bookrecord_icon


                )

            }

            Row {
                MenuButton(
                    onClick = { navController.navigate("record") },
                    text = "독서 기록\n하기",
                    imageResource = R.drawable.record_icon

                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("myBook") },
                    text = "나의 독서\n기록",
                    imageResource = R.drawable.mybook_icon

                )

            }
            Row {
                MenuButton(
                    onClick = { navController.navigate("extraction") },
                    text = "도서 항목 추출",
                    imageResource = R.drawable.booklogo4

                )
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                )
                MenuButton(
                    onClick = { navController.navigate("stopwatch") },
                    text = "독서 타이머",
                    imageResource = R.drawable.timer_icon

                )

            }

        }
    }
}

@Composable
fun MenuButton(
    onClick: () -> Unit,
    text: String,
    imageResource: Int
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(80.dp)
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
            Row {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(width = 48.dp, height = 48.dp)
                )
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp)
                )

            }
        }
    }
}

