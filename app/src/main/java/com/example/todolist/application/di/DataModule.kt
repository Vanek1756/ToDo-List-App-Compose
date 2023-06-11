package com.example.todolist.application.di

import android.content.Context
import com.example.todolist.storage.dao.CategoryDao
import com.example.todolist.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    fun provideBetDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

}