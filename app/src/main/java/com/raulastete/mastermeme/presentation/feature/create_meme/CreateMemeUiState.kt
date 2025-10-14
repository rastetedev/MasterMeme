package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.compose.ui.geometry.Offset
import com.raulastete.mastermeme.domain.MemeFontColor
import com.raulastete.mastermeme.domain.MemeFontType
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.toUi
import java.util.UUID

data class CreateMemeUiState(
    val editionButtonsState: EditionButtonsState = EditionButtonsState(),
    val memeTextStates: List<MemeTextState> = emptyList(),
    val fontColors: List<MemeFontColorUi> = MemeFontColor.entries.map { it.toUi() },
    val fontTypes: List<MemeFontTypeUi> = MemeFontType.entries.map { it.toUi() }
)

data class EditionButtonsState(
    val canUndo: Boolean = false,
    val canRedo: Boolean = false,
    val editMode: EditMode = EditMode.OnHold
)

sealed class EditMode {
    object OnHold : EditMode()
    data class InEdition(val currentTextOption: TextOption?) : EditMode()
}

enum class TextOption {
    FontType,
    FontSize,
    FontColor
}

data class MemeTextState(
    val uuid: String = UUID.randomUUID().toString(),
    val text: String = "",
    val position: Offset = Offset.Zero,
    val fontType: MemeFontTypeUi = MemeFontType.entries.first().toUi(),
    val fontSize: Float = 36f,
    val fontColor: MemeFontColorUi = MemeFontColor.entries.first().toUi(),
    val isInEdition : Boolean = true
)