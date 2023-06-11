package com.example.todolist.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.storage.converters.ListTaskConverter
import com.example.todolist.storage.converters.MapStateConverter
import com.example.todolist.storage.converters.StateConverter
import com.example.todolist.storage.dao.CategoryDao
import com.example.todolist.storage.entity.CategoryEntity
import com.example.todolist.storage.entity.TaskEntity

private const val DB_NAME = "app_database"

@Database(entities = [(CategoryEntity::class), (TaskEntity::class)], version = 1)
@TypeConverters(StateConverter::class, MapStateConverter::class, ListTaskConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}