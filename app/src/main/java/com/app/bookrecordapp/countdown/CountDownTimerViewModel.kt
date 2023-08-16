package com.app.bookrecordapp.countdown

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bookrecordapp.ui.theme.RunningState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CountDownTimerViewModel : ViewModel() {

    private var job: Job? = null

    private var _isRunning by mutableStateOf(RunningState.STOPPED)
    val isRunning
        get() = _isRunning

    private var _hour by mutableStateOf(0)
    var hour: Int
        get() = _hour
        set(value) {
            _hour = value
        }

    private var _minute by mutableStateOf(0)
    var minute: Int
        get() = _minute
        set(value) {
            _minute = value
        }

    private var _second by mutableStateOf(0)
    var second: Int
        get() = _second
        set(value) {
            _second = value
        }

    val leftTime = mutableStateOf(0)

    fun getTotalTimeInSeconds(): Int {
        return (hour * 3600 + minute * 60 + second)
    }

    fun addTime(currTime: Long): String {
        val setTime = getTotalTimeInSeconds() * 1000
        val addedTime = setTime + currTime
        val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        val date = Date(addedTime)
        return sdf.format(date)
    }

    fun stopCountDownTimer() {
        job?.cancel()
        _isRunning = RunningState.STOPPED
    }

    fun startCountDownTimer() {
        leftTime.value = getTotalTimeInSeconds()
        if (leftTime.value <= 0) return
        _isRunning = RunningState.STARTED
        job = decreaseSecond().onEach {
            leftTime.value = it
        }.launchIn(viewModelScope)
    }

    private fun decreaseSecond(): Flow<Int> = flow {
        var i = leftTime.value
        while (i > 0) {
            delay(1000)
            emit(--i)
        }
    }
}
