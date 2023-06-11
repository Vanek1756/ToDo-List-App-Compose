package com.example.todolist.ui.categories

import androidx.lifecycle.ViewModel
import com.example.todolist.ui.loader.CategoriesLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesLoader: CategoriesLoader
): ViewModel() {

    fun getCategoriesStateFlow() = categoriesLoader.categoriesStateFlow

    init {
        categoriesLoader.collectCategoriesScreen()
    }

}