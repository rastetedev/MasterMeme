package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.feature.create_meme.components.DiscardMemeCreationDialog
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontColorConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontSizeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontTypeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.MainOptions
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextConfigurationOptions
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.TextConfigurationOption
import com.raulastete.mastermeme.presentation.ui.components.NavigationalTopBar

@Composable
fun CreateMemeScreen(
    @DrawableRes templateResourceId: Int,
    viewModel: CreateMemeViewModel = viewModel(),
    navigateBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateMemeScreenContent(
        templateResourceId = templateResourceId,
        uiState = uiState,
        navigateBack = navigateBack,
        onDiscardLatestEdition = { viewModel.discardLatestEdition() },
        onConfirmLatestEdition = { viewModel.confirmLatestEdition() },
        onSelectEditFontTypeOption = { viewModel.selectEditFontTypeOption() },
        onSelectEditFontSizeOption = { viewModel.selectEditFontSizeOption() },
        onSelectEditFontColorOption = { viewModel.selectEditFontColorOption() },
        onUpdateFontSize = { viewModel.updateFontSize(it) },
        onUpdateFontColor = { viewModel.updateFontColor(it) },
        onUpdateFontType = { viewModel.updateFontType(it) },
        onUndoEdition = {},
        onRedoEdition = {},
        onAddTextBox = { viewModel.addMemeText() },
        onSaveMeme = {},
        onExitEditingText = {},
        onSetImageBounds = { offset, size -> viewModel.setImageBounds(offset, size) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateMemeScreenContent(
    @DrawableRes templateResourceId: Int,
    uiState: CreateMemeUiState,
    navigateBack: () -> Unit,
    onDiscardLatestEdition: () -> Unit,
    onConfirmLatestEdition: () -> Unit,
    onSelectEditFontTypeOption: () -> Unit,
    onSelectEditFontSizeOption: () -> Unit,
    onSelectEditFontColorOption: () -> Unit,
    onUpdateFontSize: (Float) -> Unit,
    onUpdateFontColor: (MemeFontColorUi) -> Unit,
    onUpdateFontType: (MemeFontTypeUi) -> Unit,
    onUndoEdition: () -> Unit,
    onRedoEdition: () -> Unit,
    onAddTextBox: () -> Unit,
    onSaveMeme: () -> Unit,
    onExitEditingText: () -> Unit,
    onSetImageBounds: (Offset, Size) -> Unit
) {
    val resources = LocalResources.current

    val initialData = uiState.initialData
    val editionButtonsState = uiState.editionButtonsState

    val imageBitmap =
        remember(templateResourceId) { ImageBitmap.imageResource(resources, templateResourceId) }
    val imageAspectRatio = remember(imageBitmap) {
        imageBitmap.width.toFloat() / imageBitmap.height.toFloat()
    }
    var onNavigateBackFlag by remember { mutableStateOf(false) }

    BackHandler {
        onNavigateBackFlag = true
    }

    Scaffold(
        topBar = {
            NavigationalTopBar(
                title = stringResource(R.string.create_meme_title),
                onNavigateBack = {
                    onNavigateBackFlag = true
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (editionButtonsState.editMode is EditionButtonsState.EditMode.Active) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                uiState.editionSource?.textId?.let { textInEditionId ->
                                    val containerOffset = initialData.imageBounds.offset

                                    val currentMemeTextInEditionPosition =
                                        uiState.memeTextStates.first { it.uuid == textInEditionId }.position

                                    val boxBounds = Rect(
                                        left = containerOffset.x + currentMemeTextInEditionPosition.x,
                                        top = containerOffset.y + currentMemeTextInEditionPosition.y,
                                        right = containerOffset.x + currentMemeTextInEditionPosition.x + 200f,
                                        bottom = containerOffset.y + currentMemeTextInEditionPosition.y + 100f,
                                    )

                                    if (!boxBounds.contains(offset)) {
                                        onExitEditingText()
                                    }
                                }
                            }
                        }
                )
            }

            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .aspectRatio(imageAspectRatio)
                    .onGloballyPositioned { layoutCoordinates ->

                        val imagePositionInRoot = layoutCoordinates.positionInRoot()
                        val containerSize = layoutCoordinates.size

                        val imageSize =
                            if (containerSize.width / containerSize.height.toFloat() > imageAspectRatio) {
                                Size(
                                    width = containerSize.height * imageAspectRatio,
                                    height = containerSize.height.toFloat()
                                )
                            } else {
                                Size(
                                    width = containerSize.width.toFloat(),
                                    height = containerSize.width / imageAspectRatio
                                )
                            }

                        val centeredImageOffset = Offset(
                            x = (containerSize.width - imageSize.width) / 2f,
                            y = (containerSize.height - imageSize.height) / 2f
                        )

                        val finalImageOffsetInRoot = imagePositionInRoot + centeredImageOffset

                        onSetImageBounds(finalImageOffsetInRoot, imageSize)
                    }
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                val currentMode = uiState.editionButtonsState.editMode

                if (currentMode is EditionButtonsState.EditMode.Active) {

                    ConfigurationOptions(
                        fontTypes = initialData.fontTypes,
                        fontColors = initialData.fontColors,
                        textOption = currentMode.currentTextOption,
                        selectedFontType = uiState.textInEdition?.fontType,
                        selectedFontSize = uiState.textInEdition?.fontSize,
                        selectedFontColor = uiState.textInEdition?.fontColor,
                        onUpdateFontSize = onUpdateFontSize,
                        onUpdateFontColor = onUpdateFontColor,
                        onUpdateFontType = onUpdateFontType
                    )

                    TextConfigurationOptions(
                        modifier = Modifier.fillMaxWidth(),
                        onDiscardChanges = onDiscardLatestEdition,
                        onConfirmChanges = onConfirmLatestEdition,
                        onSelectFontTypeOption = onSelectEditFontTypeOption,
                        onSelectEditFontSizeOption = onSelectEditFontSizeOption,
                        onSelectEditFontColorOption = onSelectEditFontColorOption,
                        optionSelected = currentMode.currentTextOption
                    )

                } else {
                    val onHoldMode = (currentMode as EditionButtonsState.EditMode.OnHold)

                    MainOptions(
                        modifier = Modifier.fillMaxWidth(),
                        canUndo = onHoldMode.canUndo,
                        canRedo = onHoldMode.canRedo,
                        onUndoEdition = onUndoEdition,
                        onRedoEdition = onRedoEdition,
                        onAddTextBox = onAddTextBox,
                        onSaveMeme = onSaveMeme
                    )
                }
            }
        }

        if (onNavigateBackFlag &&
            uiState.showDiscardChangesConfirmationDialog
        ) {
            DiscardMemeCreationDialog(
                onDismiss = { onNavigateBackFlag = false },
                onConfirm = {
                    onNavigateBackFlag = false
                    navigateBack()
                }
            )
        }
    }
}

@Composable
private fun ConfigurationOptions(
    fontTypes: List<MemeFontTypeUi>,
    fontColors: List<MemeFontColorUi>,
    textOption: TextConfigurationOption?,
    selectedFontType: MemeFontTypeUi?,
    selectedFontSize: Float?,
    selectedFontColor: MemeFontColorUi?,
    onUpdateFontType: (MemeFontTypeUi) -> Unit,
    onUpdateFontSize: (Float) -> Unit,
    onUpdateFontColor: (MemeFontColorUi) -> Unit
) {
    AnimatedContent(
        targetState = textOption,
        label = "TextOptionAnimation"
    ) { option ->
        when (option) {
            TextConfigurationOption.FontType -> FontTypeConfiguration(
                modifier = Modifier.fillMaxWidth(),
                selectedFontType = selectedFontType,
                fontTypeList = fontTypes,
                onTypeSelected = onUpdateFontType
            )

            TextConfigurationOption.FontSize -> FontSizeConfiguration(
                modifier = Modifier.fillMaxWidth(),
                fontSizeFloat = selectedFontSize,
                onFontSizeFloatChange = onUpdateFontSize
            )

            TextConfigurationOption.FontColor -> FontColorConfiguration(
                modifier = Modifier.fillMaxWidth(),
                selectedColor = selectedFontColor,
                fontColorList = fontColors,
                onColorSelected = onUpdateFontColor
            )

            null -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateMemeScreenContentPreview() {
    MaterialTheme {
        CreateMemeScreenContent(
            uiState = CreateMemeUiState(),
            templateResourceId = 0,
            navigateBack = {},
            onUpdateFontSize = {},
            onUpdateFontColor = {},
            onUpdateFontType = {},
            onUndoEdition = {},
            onRedoEdition = {},
            onAddTextBox = {},
            onSaveMeme = {},
            onSelectEditFontTypeOption = {},
            onSelectEditFontSizeOption = {},
            onSelectEditFontColorOption = {},
            onDiscardLatestEdition = {},
            onConfirmLatestEdition = {},
            onExitEditingText = {},
            onSetImageBounds = { _, _ -> }
        )
    }
}