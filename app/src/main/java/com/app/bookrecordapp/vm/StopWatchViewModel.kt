package com.app.bookrecordapp.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class StopWatchViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var formattedTime = mutableStateOf("00:00:000")
        private set

    private var isActive = false
    private var timeMillis = 0L
    private var lastTime = 0L

    fun start() {
        if (isActive) return

        coroutineScope.launch {
            lastTime = System.currentTimeMillis()
            isActive = true
            while (isActive) {
                delay(10L)
                timeMillis += System.currentTimeMillis() - lastTime
                lastTime = System.currentTimeMillis()
                formattedTime.value = formatTime(timeMillis)
            }
        }
    }

    fun pause() {
        isActive = false
    }

    fun reset() {
        coroutineScope.cancel()
        timeMillis = 0L
        lastTime = 0L
        formattedTime.value = "00:00:000"
        isActive = false
    }

    private fun formatTime(timeMillis: Long): String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMillis),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern(
            "mm:ss:SSS",
            Locale.getDefault()
        )
        return localDateTime.format(formatter)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
