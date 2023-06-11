package com.example.todolist.ui.home.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R

@Composable
fun TodayTasks() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        val listOfTasks = listOf<Int>()
        if (listOfTasks.isEmpty()) {
            ImageEmptyTask()
        } else {
            TodayTaskRow()
        }
//        HelloText()
//        CategoriesTitleText()
//        val categoriesItems by viewModel.getCategoriesStateFlow().collectAsStateWithLifecycle()
//        CategoriesRow(categoriesItems) {
//            // TODO() Edit categories
//        }
//        TabScreen()
    }
}

@Composable
fun TodayTaskRow() {
    // TODO() List of Tasks
}

@Composable
fun ImageEmptyTask(){
    Image(
        painter = painterResource(id = R.drawable.ic_calendar_for_empty_screen),
        contentDescription = "Empty tasks",
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp)
    )
}