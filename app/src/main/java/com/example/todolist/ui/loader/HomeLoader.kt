package com.example.todolist.ui.loader

import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.storage.source.DatabaseCategorySource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeLoader @Inject constructor(
    databaseCategorySource: DatabaseCategorySource,
    categoriesLoader: CategoriesLoader
) {

    private val homeCategoriesMutableStateFlow: MutableStateFlow<List<CategoryEntity>> =
        MutableStateFlow(emptyList())
    val homeCategoriesStateFlow = homeCategoriesMutableStateFlow.asStateFlow()

    private val scopeIo = CoroutineScope(Dispatchers.IO)

    init {
        scopeIo.launch {
            categoriesLoader.categoriesStateFlow.collectLatest {
                collectHomeScreen(it)
            }
        }
    }
    fun collectHomeScreen(categories: List<CategoryEntity>) {
        homeCategoriesMutableStateFlow.value = categories
    }
}