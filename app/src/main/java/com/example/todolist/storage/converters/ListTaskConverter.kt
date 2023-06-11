package com.example.todolist.storage.converters

import androidx.room.TypeConverter
import com.example.todolist.storage.entity.TaskEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTaskConverter {
    @TypeConverter
    fun fromListTask(list: List<TaskEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListTask(json: String): List<TaskEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<TaskEntity>>() {}.type
        return gson.fromJson(json, type)
    }
}
