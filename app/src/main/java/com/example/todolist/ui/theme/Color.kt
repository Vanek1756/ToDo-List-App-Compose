package com.example.todolist.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Blue40 = Color(0xFF8495D0)
val Green40 = Color(0xFFEAFEF2)
val Purple40 = Color(0xFFF4EAFE)

val Blue80 = Color(0xFF313D68)
val Green80 = Color(0xFF537260)
val Purple80 = Color(0xFF6C6277)

val gradientColors40 = listOf(
    Green40,
    Purple40
)

val gradientColors80 = listOf(
    Green80,
    Purple80
)

val lightGradientBrush = Brush.linearGradient(
    colors = gradientColors40,
    start = Offset(1f, 0f), // початкова точка градієнту (верхній правий кут)
    end = Offset(0f, 1f), // кінцева точка градієнту (нижній лівий кут)
)

val darkGradientBrush = Brush.linearGradient(
    colors = gradientColors80,
    start = Offset(1f, 0f), // початкова точка градієнту (верхній правий кут)
    end = Offset(0f, 1f) , // кінцева точка градієнту (нижній лівий кут)
)
