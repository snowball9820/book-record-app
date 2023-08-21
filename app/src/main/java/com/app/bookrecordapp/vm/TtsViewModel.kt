package com.app.bookrecordapp.vm

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.bookrecordapp.screen.TtsScreenState
import java.util.Locale

class TtsViewModel: ViewModel() {

    private val _state = mutableStateOf(TtsScreenState())
    val state: State<TtsScreenState> = _state
    private  var  textToSpeech: TextToSpeech? = null


    fun onTextFieldValueChange(text:String){
        _state.value = state.value.copy(
            text = text
        )
    }

    fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.KOREAN
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }
    }
}
