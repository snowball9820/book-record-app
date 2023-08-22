package com.app.bookrecordapp.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.rememberImagePainter
import com.app.bookrecordapp.data.AppDatabase
import com.app.bookrecordapp.data.User
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@Composable
fun TranslationScreen(navController: NavController) {

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


    val koEnTranslator = remember {

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        Translation.getClient(options)
    }
    val koJaTranslator = remember {

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.JAPANESE)
            .build()
        Translation.getClient(options)

    }

    val koChTranslator = remember {

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.CHINESE)
            .build()
        Translation.getClient(options)
    }

    var enabled by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        koEnTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                enabled = true

            }
        koJaTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                enabled = true

            }
        koChTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                enabled = true

            }


            .addOnFailureListener { exception ->

            }

    }




    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var selectUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectUri = uri

            }
        )


        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,

                ),
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
                .padding(top = 12.dp)
        ) {
            Text(
                "문장 등록",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )

        }
        Spacer(modifier = Modifier.padding(12.dp))

        val koRecognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        val context = LocalContext.current
        var trText by remember { mutableStateOf("") }
        var enText by remember { mutableStateOf("") }
        var jaText by remember { mutableStateOf("") }
        var chText by remember { mutableStateOf("") }

        selectUri?.let {
            try {
                val image = InputImage.fromFilePath(context, it)
                koRecognizer.process(image)
                    .addOnSuccessListener { result ->
                        trText = result.text
                    }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    )

                ) {
                    Image(
                        painter = rememberImagePainter(data = selectUri),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Divider(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(12.dp))

                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    )
                ) {
                    Text(
                        text = trText,
                        fontSize = 20.sp,
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
                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = {
                        koEnTranslator.translate(trText)
                            .addOnSuccessListener { translatedText ->
                                enText = translatedText


                            }
                        koJaTranslator.translate(trText)
                            .addOnSuccessListener { translatedText ->
                                jaText = translatedText


                            }
                        koChTranslator.translate(trText)
                            .addOnSuccessListener { translatedText ->
                                chText = translatedText


                            }

                        val newUser = User(
                            textId = "",
                            textPw = "",
                            text = trText,
                            title = "",
                            description = "",
                            selectedImageUri = null

                        )

                        scope.launch(Dispatchers.IO) {
                            db.userDao().insertAll(newUser)
                        }


                    }, enabled = enabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,

                        ),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)


                ) {

                    Text(
                        text = "번역",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )


                }

                Spacer(modifier = Modifier.padding(12.dp))

                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    )
                ) {
                    Text(
                        text = enText,
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
                        )
                    )

                    Text(
                        text = jaText,
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

                    Text(
                        text = chText,
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
                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = {
                        val clipboardManager =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("번역", enText)
                        clipboardManager.setPrimaryClip(clip)


                        Toast.makeText(context, "클립 보드에 복사 되었습니다", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = "복사",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                }


            }


        }
    }


}
