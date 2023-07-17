package com.example.todolist.storage.source

import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.storage.repository.DatabaseCategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseCategorySource @Inject constructor(private val databaseCategoryRepository: DatabaseCategoryRepository) {

    private val insertFinishMutableStateFlow: MutableStateFlow<CategoryEntity?> = MutableStateFlow(null)
    val insertFinishStateFlow: StateFlow<CategoryEntity?> = insertFinishMutableStateFlow
    private val deleteFinishMutableStateFlow: MutableStateFlow<CategoryEntity?> = MutableStateFlow(null)
    val deleteFinishStateFlow = deleteFinishMutableStateFlow.asStateFlow()

    private val scopeIo = CoroutineScope(Dispatchers.IO)

    suspend fun getCategoriesStateFlow() = databaseCategoryRepository.getCategory().stateIn(scopeIo)

    fun insertCategory(categoryName: String, categoryColor: String) {
        scopeIo.launch {
            val categoryEntity = CategoryEntity(0, categoryName, emptyList(),categoryColor)
            databaseCategoryRepository.insertCategory(categoryEntity).apply {
                insertFinishMutableStateFlow.value = categoryEntity
            }
        }
    }

    fun updateCategory(categoryEntity: CategoryEntity) {
        scopeIo.launch {
            databaseCategoryRepository.insertCategory(categoryEntity).apply {
                insertFinishMutableStateFlow.value = categoryEntity
            }
        }
    }

    fun deleteCategory(categoryEntity: CategoryEntity) {
        scopeIo.launch {
            databaseCategoryRepository.deleteCategory(categoryEntity.categoryId)
        }
    }
}