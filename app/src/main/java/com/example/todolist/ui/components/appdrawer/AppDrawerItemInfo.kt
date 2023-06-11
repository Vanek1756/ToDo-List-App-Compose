package com.example.todolist.ui.components.appdrawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class AppDrawerItemInfo<T>(
    val drawerOption: T,
    @StringRes val title: Int,
    val imageVector: ImageVector?,
    @DrawableRes val drawableId: Int?,
    @StringRes val descriptionId: Int
)