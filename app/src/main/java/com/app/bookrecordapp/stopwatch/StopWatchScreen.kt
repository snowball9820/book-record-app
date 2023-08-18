package com.app.bookrecordapp.stopwatch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.bookrecordapp.vm.StopWatchViewModel

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