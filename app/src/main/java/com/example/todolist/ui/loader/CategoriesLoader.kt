package com.example.todolist.ui.loader

import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.storage.source.DatabaseCategorySource
import com.example.todolist.ui.utils.Items
import com.example.todolist.ui.utils.mapToItemsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesLoader @Inject constructor(private val databaseCategorySource: DatabaseCategorySource) {

    private val categoriesScreenMutableStateFlow: MutableStateFlow<List<Items<*>>> =
        MutableStateFlow(emptyList())
    val categoriesScreenStateFlow = categoriesScreenMutableStateFlow.asStateFlow()

    private val categoriesMutableStateFlow: MutableStateFlow<List<CategoryEntity>> =
        MutableStateFlow(emptyList())
    val categoriesStateFlow = categoriesMutableStateFlow.asStateFlow()

    fun getInsertFinishStateFlow() = databaseCategorySource.insertFinishStateFlow

    private val scopeIo = CoroutineScope(Dispatchers.IO)

    init {
        scopeIo.launch {
            databaseCategorySource.getCategoriesStateFlow().collectLatest {
                collectCategoriesScreen(it)
            }
        }
    }

    private fun collectCategoriesScreen(categoryEntities: List<CategoryEntity>) {
        val listOfCategories = mutableListOf<Any>()
        listOfCategories.addAll(categoryEntities)
        listOfCategories.add(1111)
        categoriesScreenMutableStateFlow.value = listOfCategories.mapToItemsList()
        categoriesMutableStateFlow.value = categoryEntities
    }

    fun createNewCategory(newCategoryName: String, newCategoryColor: String) {
        databaseCategorySource.insertCategory(newCategoryName, newCategoryColor)
    }
}