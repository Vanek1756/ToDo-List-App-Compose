package com.example.todolist.ui.home.pending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.todolist.ui.home.today.ImageEmptyTask

@Composable
fun PendingTasks() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        val listOfTasks = listOf<Int>()
        if (listOfTasks.isEmpty()) {
            ImageEmptyTask()
        } else {
            PendingTaskRow()
        }
    }
}

@Composable
fun PendingTaskRow() {
    // TODO() List of Tasks
}
