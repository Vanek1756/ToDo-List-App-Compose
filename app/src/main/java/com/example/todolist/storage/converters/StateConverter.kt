package com.example.todolist.storage.converters

import androidx.room.TypeConverter
import com.example.todolist.ui.utils.State

class StateConverter {

    @TypeConverter
    fun toState(value: String) = enumValueOf<State>(value)

    @TypeConverter
    fun fromHealth(value: State) = value.name
}