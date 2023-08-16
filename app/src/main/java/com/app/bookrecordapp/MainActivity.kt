package com.app.bookrecordapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.app.bookrecordapp.compose.TranslationScreen
import com.app.bookrecordapp.data.AppDatabase
import com.app.bookrecordapp.data.User
import com.app.bookrecordapp.ui.theme.BookRecordAppTheme
import com.app.bookrecordapp.vm.StopWatchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import java.time.format.DateTimeFormatter
import java.util.UUID


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navi()

        }
    }
}

//class MyViewModel : ViewModel() {
//    private var savedTextId = ""
//    private var savedTextPw = ""
//
//    fun isButtonEnabled(textId: String, textPw: String): Boolean {
//        return textId.isNotEmpty() && textPw.isNotEmpty()
//    }
//
//    fun onButtonClicked(textId: String, textPw: String) {
//        savedTextId = textId
//        savedTextPw = textPw
//
//    }
//}

@Composable
fun Navi() {

    BookRecordAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "membership") {
                composable("membership") {
                    MembershipScreen(navController)
                }
                composable("registration") {
                    RegistrationScreen(navController)
                }
                composable("menu") {
                    MenuScreen(navController)
                }
                composable("translation") {
                    TranslationScreen(navController)
                }
                composable("record") {
                    ReadingRecordScreen(navController)
                }
                composable("extraction") {
                    ExtractionScreen(navController)
                }
                composable("stopwatch") {
                    StopWatchScreen(navController)
                }


            }

        }
    }

}


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
                        textPw = textPw

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

@Composable
fun RegistrationScreen(navController: NavController) {

    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "회원 등록이 완료 되었습니다.")

            Button(
                onClick = {
                    navController.navigate("menu")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
            ) {
                Text(text = "시작")

            }

        }


    }


}

@Composable
fun MenuScreen(navController: NavController) {
    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("translation") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "문장 추출&번역",
                    fontSize = 20.sp)

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(onClick = { navController.navigate("record") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {

                Text(text = "독서 기록",
                    fontSize = 20.sp)

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(onClick = { navController.navigate("extraction") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(text = "도서 항목 추출",
                    fontSize = 20.sp)

            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(onClick = { navController.navigate("stopwatch") },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp)
            ) {
                Text(text = "독서 타이머",
                    fontSize = 20.sp)

            }


        }


    }
}



@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}
data class Note(
    val id:UUID=UUID.randomUUID(),
    val title:String,
    val description:String,
    val entryDate:LocalDateTime=LocalDateTime.now()
) {
    companion object {
        fun create(title: String, description: String): Note {
            return Note(title = title, description = description)
        }
    }
}
//class NotesDataSource{
//    fun loadNotes():List<Note>{
//        return listOf(
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~"),
//            Note(title="day1", description = "~~~")
//        )
//    }
//}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
){
    Surface(
        modifier
            .clickable { onNoteClicked(note) }
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = note.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReadingRecordScreen(navController: NavController) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val notes = remember { mutableStateListOf<Note>() }

    fun removeNoteById(noteId: UUID) {
        notes.removeIf { it.id == noteId }
    }



    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteInputText(
            Modifier
                .padding(top=9.dp, bottom=8.dp),
            text = title,
            label = "제목",
            onTextChange = {
                if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) title = it
            }
        )
        NoteInputText(
            Modifier
                .padding(top=9.dp, bottom=8.dp),
            text = description,
            label = "기록 추가",
            onTextChange = {
                if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) description = it
            }
        )

        NoteButton(
            text = "저장",
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val newNote = Note.create(title, description)
                    notes.add(newNote)
                    title = ""
                    description = ""
                }
            }
        )
        Divider(modifier = Modifier.padding(10.dp))
//
//        val notesDataSource = NotesDataSource()


        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    removeNoteById(note.id)
                })
            }
        }


    }


}


@Composable
fun ExtractionScreen(navController: NavController) {

    Box() {

        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
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
                        containerColor = MaterialTheme.colorScheme.primary,

                        ),
                    modifier = Modifier
                        .width(120.dp)
                        .height(60.dp)
                ) {
                    Text(
                        "항목",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

            }


        }

    }
}

//@Composable
//fun StopWatchScreen(navController: NavController){
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val stopWatch = remember { StopWatch() }
//        StopWatchDisplay(
//            formattedTime = stopWatch.formattedTime,
//            onStartClick = stopWatch::start,
//            onPauseClick = stopWatch::pause,
//            onResetClick = stopWatch::reset
//        )
//    }
//
//}
@Composable
fun StopWatchScreen(navController: NavController) {
    val viewModel: StopWatchViewModel = viewModel()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        StopWatchDisplay(
            formattedTime = viewModel.formattedTime.value,
            onStartClick = viewModel::start,
            onPauseClick = viewModel::pause,
            onResetClick = viewModel::reset
        )
    }
}

@Composable
fun StopWatchDisplay(
    formattedTime: String,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedTime,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onStartClick) {
                Text("Start")
            }
            Spacer(Modifier.width(16.dp))
            Button(onPauseClick) {
                Text("Pause")
            }
            Spacer(Modifier.width(16.dp))
            Button(onResetClick) {
                Text("Reset")
            }

        }
    }
}
//class StopWatch {
//
//    var formattedTime by mutableStateOf("00:00:000")
//    private var coroutineScope = CoroutineScope(Dispatchers.Main)
//    private var isActive = false
//
//    private var timeMillis = 0L
//    private var lastTimestamp = 0L
//
//    fun start() {
//        if (isActive) return
//
//        coroutineScope.launch {
//            lastTimestamp = System.currentTimeMillis()
//            this@StopWatch.isActive = true
//            while(this@StopWatch.isActive) {
//                delay(10L)
//                timeMillis += System.currentTimeMillis() - lastTimestamp
//                lastTimestamp = System.currentTimeMillis()
//                formattedTime = formatTime(timeMillis)
//            }
//        }
//    }
//    fun pause() {
//        isActive = false
//    }
//
//    fun reset() {
//        coroutineScope.cancel()
//        coroutineScope = CoroutineScope(Dispatchers.Main)
//        timeMillis = 0L
//        lastTimestamp = 0L
//        formattedTime = "00:00:000"
//        isActive = false
//    }
//
//    private fun formatTime(timeMillis: Long): String {
//        val localDateTime = ofInstant(
//            Instant.ofEpochMilli(timeMillis),
//            ZoneId.systemDefault()
//        )
//        val formatter = DateTimeFormatter.ofPattern(
//            "mm:ss:SSS",
//            Locale.getDefault()
//        )
//        return localDateTime.format(formatter)
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookRecordAppTheme {

    }
}