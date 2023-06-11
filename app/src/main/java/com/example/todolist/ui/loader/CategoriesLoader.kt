package com.example.todolist.ui.loader

import android.graphics.Color
import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.storage.source.DatabaseCategorySource
import com.example.todolist.ui.utils.Items
import com.example.todolist.ui.utils.State
import com.example.todolist.ui.utils.mapToItemsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesLoader @Inject constructor(databaseCategorySource: DatabaseCategorySource) {

    private val categoriesMutableStateFlow: MutableStateFlow<List<Items<*>>> =
        MutableStateFlow(emptyList())
    val categoriesStateFlow = categoriesMutableStateFlow.asStateFlow()

    fun collectCategoriesScreen() {
        val listOfCategories = mutableListOf<Any>()
        repeat(5) { index ->
            val category = CategoryEntity(0, "Category $index", emptyList(), Color.BLUE)
            repeat(index) {
                category.addNewTask(
                    "Task number $it",
                    "06/06/2023",
                    "09:4$it",
                    false,
                    mutableMapOf(State.DONE to (it % 2 == 0))
                )
            }
            listOfCategories.add(category)
        }
        listOfCategories.add(1111)
        categoriesMutableStateFlow.value = listOfCategories.mapToItemsList()
    }
}