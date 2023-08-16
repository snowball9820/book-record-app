//package com.app.bookrecordapp.countdown
//
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.app.bookrecordapp.ui.theme.RunningState
//
//@Composable
//fun CountDownTimerScreen(isDarkTheme: MutableState<Boolean>) {
//    val viewModel: CountDownTimerViewModel = viewModel()
//    val isCountDownTimerVisible = viewModel.isRunning
//    val addedTime = viewModel.addTime(System.currentTimeMillis())
//    val dialogState = remember { mutableStateOf(false) }
//
//    if (dialogState.value) {
//        ShowDialog {
//            dialogState.value = false
//        }
//    }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.padding(24.dp)
//    ) {
//        IconButton(
//            onClick = {
//                isDarkTheme.value = !isDarkTheme.value
//            },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Icon(
//                imageVector = Icons.Default.RemoveRedEye,
//                contentDescription = null
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        SelectTime(runningState = isCountDownTimerVisible, viewModel = viewModel)
//        CircularCountDownTimer(
//            runningState = isCountDownTimerVisible,
//            viewModel = viewModel,
//            addedTime = addedTime,
//            dialogState = dialogState
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//
//            Button(
//                onClick = {
//                    when (viewModel.isRunning) {
//                        RunningState.STOPPED -> viewModel.startCountDownTimer()
//                        RunningState.STARTED -> viewModel.stopCountDownTimer()
//                    }
//                },
//                modifier = Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(16.dp))
//
//            ) {
//                Text(
//                    text = if (viewModel.isRunning == RunningState.STOPPED)
//                        "Start"
//                    else
//                        "Stop"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun SelectTime(
//    runningState: RunningState,
//    viewModel: CountDownTimerViewModel
//) {
//    if (runningState == RunningState.STOPPED) {
//        Box(
//            modifier = Modifier.fillMaxWidth().height(350.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Box(
//                modifier = Modifier.fillMaxWidth().height(36.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color.LightGray)
//                    .align(Alignment.Center),
//            )
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Row {
//                    NumberPickerList(numbers = HOUR_LIST) {
//                        viewModel.hour = it
//                    }
//
//                    Text(text = "h", modifier = Modifier.align(Alignment.CenterVertically))
//                }
//
//                Row {
//                    NumberPickerList(numbers = MINUTE_LIST) {
//                        viewModel.minute = it
//                    }
//
//                    Text(text = "m", modifier = Modifier.align(Alignment.CenterVertically))
//                }
//                Row {
//                    NumberPickerList(numbers = SECOND_LIST) {
//                        viewModel.second = it
//                    }
//
//                    Text(text = "s", modifier = Modifier.align(Alignment.CenterVertically))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CircularCountDownTimer(
//    runningState: RunningState,
//    viewModel: CountDownTimerViewModel,
//    dialogState: MutableState<Boolean>,
//    addedTime: String
//) {
//    if (runningState != RunningState.STOPPED) {
//        val leftTime = viewModel.leftTime.value
//        if (leftTime == 0) {
//            dialogState.value = true
//            viewModel.stopCountDownTimer()
//        }
//        val progress =
//            remember { Animatable(leftTime / viewModel.getTotalTimeInSeconds().toFloat()) }
//        val progressTarget = 0f
//
//        LaunchedEffect(runningState == RunningState.STARTED) {
//            progress.animateTo(
//                targetValue = progressTarget,
//                animationSpec = tween(
//                    durationMillis = leftTime * 1000,
//                    easing = LinearEasing
//                )
//            )
//        }
//        Box(
//            modifier = Modifier.size(350.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator(
//                modifier = Modifier.fillMaxSize(),
//                color = Color.LightGray,
//                progress = 100f,
//                strokeWidth = 10.dp
//            )
//
//            CircularProgressIndicator(
//                modifier = Modifier.fillMaxSize(),
//                progress = progress.value,
//                strokeWidth = 10.dp
//            )
//
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(
//                    text = "${formatTime(isLeadingZeroNeeded = true, value = leftTime / 3600)}:" +
//                            "${
//                                formatTime(isLeadingZeroNeeded = true, value = (leftTime / 60) % 60)
//                            }:" +
//                            formatTime(isLeadingZeroNeeded = true, value = leftTime % 60),
//                    fontSize = 48.sp
//
//                )
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        imageVector = Icons.Default.AccessAlarm,
//                        modifier = Modifier.padding(4.dp),
//                        contentDescription = null
//                    )
//                    Text(
//                        text = addedTime
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ShowDialog(onDismiss: () -> Unit) {
//    AlertDialog(
//        text = {
//            Text(
//                text = "Watch Out!",
//                modifier = Modifier.padding(8.dp),
//                style = MaterialTheme.typography.headlineSmall
//            )
//        },
//        buttons = {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                TextButton(
//                    onClick = onDismiss,
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text(text = "Ok")
//                }
//            }
//        },
//        onDismissRequest = onDismiss
//    )
//}
//
