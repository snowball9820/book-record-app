package com.app.bookrecordapp.vm

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.bookrecordapp.screen.TtsScreenState
import java.util.Locale

class TtsViewModel : ViewModel() {

    val state = mutableStateOf(TtsScreenState())
    private var textToSpeech: TextToSpeech? = null

    fun onTextFieldValueChange(text: String) {
        state.value = state.value.copy(text = text)
    }

    fun textToSpeech(context: Context) {
        state.value = state.value.copy(isButtonEnabled = false)

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.apply {
                    language = Locale.KOREAN
                    setSpeechRate(1.0f)
                    speak(state.value.text, TextToSpeech.QUEUE_ADD, null, null)
                }
            }
            state.value = state.value.copy(isButtonEnabled = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}
