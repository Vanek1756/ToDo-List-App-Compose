package com.example.todolist.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.storage.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity): Long

    @Query("DELETE FROM category_table WHERE category_id=:categoryId")
    suspend fun deleteCategory(categoryId: Int): Int

    @Query("SELECT * FROM category_table")
    fun getCategory(): Flow<List<CategoryEntity>>

}