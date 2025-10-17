package com.raulastete.mastermeme.presentation.model

import androidx.compose.ui.graphics.Color

enum class MemeFontColorUi(
    val color: Color,
    val outlineColor: Color
) {
    WHITE(Color.White, Color.Black),
    RED(Color.Red, Color.Black),
    GREEN(Color.Green, Color.Black),
    BLUE(Color.Blue, Color.White),
    YELLOW(Color.Yellow, Color.Black),
    ORANGE(Color(0xFFFFA500), Color.Black),
    PURPLE(Color(0xFF800080), Color.White),
    PINK(Color(0xFFFFC0CB), Color.Black),
    BROWN(Color(0xFFA52A2A), Color.White),
    CYAN(Color.Cyan, Color.Black),
    MAGENTA(Color.Magenta, Color.Black),
    GRAY(Color.Gray, Color.Black),
    BLACK(Color.Black, Color.White)
}