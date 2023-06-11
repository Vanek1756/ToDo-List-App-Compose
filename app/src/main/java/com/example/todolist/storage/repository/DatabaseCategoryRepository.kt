package com.example.todolist.storage.repository

import androidx.annotation.WorkerThread
import com.example.todolist.storage.dao.CategoryDao
import com.example.todolist.storage.database.DatabaseCategoryHelper
import com.example.todolist.storage.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : DatabaseCategoryHelper {

    @WorkerThread
    override suspend fun insertCategory(categoryEntity: CategoryEntity): Long = categoryDao.insertCategory(categoryEntity)

    @WorkerThread
    override suspend fun deleteCategory(categoryId: Int): Int = categoryDao.deleteCategory(categoryId)

    override suspend fun getCategory(): Flow<List<CategoryEntity>> = categoryDao.getCategory()
}