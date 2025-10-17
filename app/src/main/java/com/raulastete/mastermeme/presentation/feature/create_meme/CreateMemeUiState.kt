package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.MemeText
import com.raulastete.mastermeme.presentation.model.TextConfigurationOption

data class CreateMemeUiState(
    val initialData: InitialData = InitialData(),
    val editionButtonsState: EditionButtonsState = EditionButtonsState(),
    val memeTextStates: List<MemeText> = emptyList(),
    val editionSource: EditionSource? = null,
    val showSaveMemeOptions: Boolean = false,
) {
    val showDiscardChangesConfirmationDialog = memeTextStates.isNotEmpty()
            && (editionButtonsState.editMode as? EditionButtonsState.EditMode.OnHold)?.canRedo == true

    val isNewText: Boolean get() = editionSource is EditionSource.NewText
    val textInEdition = memeTextStates.firstOrNull { it.uuid == editionSource?.textId }
}

sealed class EditionSource(open val textId: String) {
    data class NewText(override val textId: String) : EditionSource(textId)
    data class ExistingText(override val textId: String, val backupForUndo: MemeText) :
        EditionSource(textId)
}

data class InitialData(
    val fontColors: List<MemeFontColorUi> = MemeFontColorUi.entries,
    val fontTypes: List<MemeFontTypeUi> = MemeFontTypeUi.entries,
    val imageBounds: ImageBounds = ImageBounds()
)

data class ImageBounds(
    val offset: Offset = Offset.Zero,
    val size: Size = Size.Zero
) {
    val right: Float get() = offset.x + size.width
    val bottom: Float get() = offset.y + size.height

    fun toRect(): Rect = Rect(offset, size)
}

data class EditionButtonsState(
    val editMode: EditMode = EditMode.OnHold()
) {
    sealed class EditMode {

        data class OnHold(
            val canUndo: Boolean = false,
            val canRedo: Boolean = false,
        ) : EditMode()

        data class Active(
            val currentTextOption: TextConfigurationOption?
        ) : EditMode()
    }
}