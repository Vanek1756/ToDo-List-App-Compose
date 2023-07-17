package com.example.todolist.ui.categories

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todolist.ui.loader.CategoriesLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesLoader: CategoriesLoader
) : ViewModel() {

    fun getCategoriesStateFlow() = categoriesLoader.categoriesScreenStateFlow
    fun getInsertFinishStateFlow() = categoriesLoader.getInsertFinishStateFlow()

    private var newCategoryName: String = ""
    private var newCategoryColor: String = ""
    fun setNewCategoryName(txt: String) {
        newCategoryName = txt
    }

    fun setNewCategoryColor(color: Color) {
        newCategoryColor = color.value.toString()
    }

    fun createNewCategory() {
        categoriesLoader.createNewCategory(newCategoryName,newCategoryColor)
    }

}