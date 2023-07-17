package com.example.todolist.ui.home

import androidx.lifecycle.ViewModel
import com.example.todolist.ui.loader.HomeLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeLoader: HomeLoader
): ViewModel() {

    fun getCategoriesStateFlow() = homeLoader.homeCategoriesStateFlow

}