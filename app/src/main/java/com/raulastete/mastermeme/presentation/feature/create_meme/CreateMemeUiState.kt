package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.compose.ui.unit.sp
import com.raulastete.mastermeme.domain.MemeFontColor
import com.raulastete.mastermeme.domain.MemeFontType
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.toUi

data class CreateMemeUiState(
    val textState: TextState = TextState(),
    val fontColors: List<MemeFontColorUi> = MemeFontColor.entries.map { it.toUi() },
    val fontTypes: List<MemeFontTypeUi> = MemeFontType.entries.map { it.toUi() }
)

data class TextState(
    val text: String = "",
    val fontType: MemeFontTypeUi = MemeFontType.entries.first().toUi(),
    val fontSize: Float = 16f,
    val fontColor: MemeFontColorUi = MemeFontColor.entries.first().toUi()
) {
    val fontSizeInSp get() = fontSize.sp
}