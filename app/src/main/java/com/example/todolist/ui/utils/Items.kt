package com.example.todolist.ui.utils

data class Items<out T>(
    val value: T
)

fun <T> List<T>.mapToItemsList() = map { Items(it) }