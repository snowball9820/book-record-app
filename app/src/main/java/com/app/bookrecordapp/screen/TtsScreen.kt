package com.app.bookrecordapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.bookrecordapp.R
import com.app.bookrecordapp.vm.TtsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TtsScreen(navController: NavController, viewModel: TtsViewModel = viewModel()) {

    val state = viewModel.state.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = state.text,
            onValueChange = { viewModel.onTextFieldValueChange(it) },
            Modifier
                .height(200.dp)
                .width(400.dp)
                .padding(top = 12.dp),
            label = { Text("TTS") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            onClick = { viewModel.textToSpeech(context) },
            text = "Play",
            imageResource = R.drawable.play_icon

        )

    }
}

data class TtsScreenState(
    val isButtonEnabled: Boolean = true,
    val text: String = ""
)
