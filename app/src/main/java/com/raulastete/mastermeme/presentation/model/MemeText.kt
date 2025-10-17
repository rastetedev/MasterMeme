package com.raulastete.mastermeme.presentation.model

import androidx.compose.ui.geometry.Offset
import java.util.UUID

private const val DEFAULT_FONT_SIZE = 36f

data class MemeText(
    val uuid: String = UUID.randomUUID().toString(),
    val text: String = "",
    val position: Offset = Offset.Zero,
    val fontType: MemeFontTypeUi = MemeFontTypeUi.entries.first(),
    val fontSize: Float = DEFAULT_FONT_SIZE,
    val fontColor: MemeFontColorUi = MemeFontColorUi.entries.first(),
)