package com.example.todolist.storage.converters

import androidx.room.TypeConverter
import com.example.todolist.ui.utils.State
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapStateConverter {
    @TypeConverter
    fun fromMap(map: Map<State, Boolean>): String {
        // Конвертувати Map в рядок JSON або інший формат для збереження в базі даних
        val gson = Gson()
        return gson.toJson(map)
    }

    @TypeConverter
    fun toMap(json: String): Map<State, Boolean> {
        // Конвертувати рядок JSON або інший формат з бази даних у Map
        val gson = Gson()
        val type = object : TypeToken<Map<State, Boolean>>() {}.type
        return gson.fromJson(json, type)
    }
}
