package com.raulastete.mastermeme.presentation.feature.create_meme

import com.raulastete.mastermeme.domain.MemeFontColor
import com.raulastete.mastermeme.domain.MemeFontType
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOption
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.toUi
import java.util.UUID

data class CreateMemeUiState(
    val editModeState: EditModeState = EditModeState(),
    val textStates: List<TextState> = emptyList(),
    val fontColors: List<MemeFontColorUi> = MemeFontColor.entries.map { it.toUi() },
    val fontTypes: List<MemeFontTypeUi> = MemeFontType.entries.map { it.toUi() }
)

data class EditModeState(
    val canUndo: Boolean = false,
    val canRedo: Boolean = false,
    val mode: EditMode = EditMode.OnHold
) {
    fun isOnHold() = mode is EditMode.OnHold

    fun isInEdition() = mode is EditMode.InEdition

    fun isFontColorConfigurationSelected() =
        (mode as? EditMode.InEdition)?.textOption == TextOption.FontColor

    fun isFontTypeConfigurationSelected() =
        (mode as? EditMode.InEdition)?.textOption == TextOption.FontType

    fun isFontSizeConfigurationSelected() =
        (mode as? EditMode.InEdition)?.textOption == TextOption.FontSize
}

sealed class EditMode {
    object OnHold : EditMode()
    data class InEdition(val textOption: TextOption?) : EditMode()
}

data class TextState(
    val uuid: String = UUID.randomUUID().toString(),
    val text: String = "",
    val fontType: MemeFontTypeUi = MemeFontType.entries.first().toUi(),
    val fontSize: Float = 16f,
    val fontColor: MemeFontColorUi = MemeFontColor.entries.first().toUi()
)