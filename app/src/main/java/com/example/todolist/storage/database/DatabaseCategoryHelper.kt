package com.example.todolist.storage.database

import com.example.todolist.storage.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseCategoryHelper {

    suspend fun insertCategory(categoryEntity: CategoryEntity): Long

    suspend fun deleteCategory(categoryId: Int): Int

    suspend fun getCategory(): Flow<List<CategoryEntity>>

}