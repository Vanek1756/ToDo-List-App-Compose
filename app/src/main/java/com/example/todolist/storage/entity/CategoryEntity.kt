package com.example.todolist.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.ui.utils.State

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "list_of_tasks")
    var listOfTasks: List<TaskEntity>,
    @ColumnInfo(name = "category_color_id")
    val categoryColorId: Int
) {
    fun addNewTask(description: String, date: String, time: String, isImportant: Boolean, property: Map<State, Boolean>) {
        val newList = this.listOfTasks.toMutableList()
        val newTask = TaskEntity(
            0,
            categoryId,
            categoryName,
            date,
            time,
            description,
            isImportant,
            property
        )
        newList.add(newTask)
        listOfTasks = newList.sortedWith(compareBy({ it.date }, { it.time }))
    }
}
