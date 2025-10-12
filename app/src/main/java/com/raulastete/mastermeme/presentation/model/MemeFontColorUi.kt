package com.raulastete.mastermeme.presentation.model

import androidx.compose.ui.graphics.Color
import com.raulastete.mastermeme.domain.MemeFontColor

data class MemeFontColorUi(
    val color: Color,
    val outlineColor: Color
)

fun MemeFontColor.toUi(): MemeFontColorUi {
    return when (this) {
        MemeFontColor.WHITE -> MemeFontColorUi(Color.White, Color.Black)
        MemeFontColor.RED -> MemeFontColorUi(Color.Red, Color.Black)
        MemeFontColor.GREEN -> MemeFontColorUi(Color.Green, Color.Black)
        MemeFontColor.BLUE -> MemeFontColorUi(Color.Blue, Color.White)
        MemeFontColor.YELLOW -> MemeFontColorUi(Color.Yellow, Color.Black)
        MemeFontColor.ORANGE -> MemeFontColorUi(Color(0xFFFFA500), Color.Black)
        MemeFontColor.PURPLE -> MemeFontColorUi(Color(0xFF800080), Color.White)
        MemeFontColor.PINK -> MemeFontColorUi(Color(0xFFFFC0CB), Color.Black)
        MemeFontColor.BROWN -> MemeFontColorUi(Color(0xFFA52A2A), Color.White)
        MemeFontColor.CYAN -> MemeFontColorUi(Color.Cyan, Color.Black)
        MemeFontColor.MAGENTA -> MemeFontColorUi(Color.Magenta, Color.Black)
        MemeFontColor.GRAY -> MemeFontColorUi(Color.Gray, Color.Black)
        MemeFontColor.BLACK -> MemeFontColorUi(Color.Black, Color.White)
    }
}