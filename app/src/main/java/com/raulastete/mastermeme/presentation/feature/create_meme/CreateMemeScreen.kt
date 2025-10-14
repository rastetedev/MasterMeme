package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontColorConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontSizeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontTypeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.MainOptions
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOptions
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
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
        onSelectEditFontTypeOption = viewModel::selectEditFontTypeOption,
        onSelectEditFontSizeOption = viewModel::selectEditFontSizeOption,
        onSelectEditFontColorOption = viewModel::selectEditFontColorOption,
        onUpdateFontSize = {},
        onUpdateFontColor = { },
        onUpdateFontType = {},
        onUndoEdition = {},
        onRedoEdition = {},
        onAddTextBox = viewModel::addNewTextBox,
        onSaveMeme = {})
}

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
    onSaveMeme: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val resources = LocalResources.current

    val imageBitmap =
        remember(templateResourceId) { ImageBitmap.imageResource(resources, templateResourceId) }
    val imageAspectRatio = imageBitmap.width.toFloat() / imageBitmap.height.toFloat()

    Scaffold(
        topBar = {
            NavigationalTopBar(
                title = stringResource(R.string.create_meme_title),
                onNavigateBack = navigateBack
            )
        }
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .aspectRatio(imageAspectRatio)
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                val currentMode = uiState.editionButtonsState.editMode

                if (currentMode is EditMode.InEdition) {

                    AnimatedContent(
                        targetState = currentMode.currentTextOption,
                        label = "TextOptionAnimation"
                    ) { option ->
                        when (option) {
                            TextOption.FontType -> FontTypeConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                selectedFontType = uiState.memeTextStates.first().fontType,
                                fontTypeList = uiState.fontTypes,
                                onTypeSelected = onUpdateFontType
                            )

                            TextOption.FontSize -> FontSizeConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                fontSizeFloat = uiState.memeTextStates.first().fontSize,
                                onFontSizeFloatChange = onUpdateFontSize
                            )

                            TextOption.FontColor -> FontColorConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                selectedColor = uiState.memeTextStates.first().fontColor,
                                fontColorList = uiState.fontColors,
                                onColorSelected = onUpdateFontColor
                            )

                            null -> {}
                        }
                    }

                    TextOptions(
                        modifier = Modifier.fillMaxWidth(),
                        onDiscardChanges = onDiscardLatestEdition,
                        onConfirmChanges = onConfirmLatestEdition,
                        onSelectFontTypeOption = onSelectEditFontTypeOption,
                        onSelectEditFontSizeOption = onSelectEditFontSizeOption,
                        onSelectEditFontColorOption = onSelectEditFontColorOption,
                        optionSelected = currentMode.currentTextOption
                    )

                } else {
                    MainOptions(
                        modifier = Modifier.fillMaxWidth(),
                        canUndo = uiState.editionButtonsState.canUndo,
                        canRedo = uiState.editionButtonsState.canRedo,
                        onUndoEdition = onUndoEdition,
                        onRedoEdition = onRedoEdition,
                        onAddTextBox = onAddTextBox,
                        onSaveMeme = onSaveMeme
                    )
                }
            }
        }
    }
}

private fun calculateImageBounds(containerSize: IntSize, imageAspectRatio: Float): Rect {

    if (containerSize.height == 0) {
        return Rect.Zero
    }

    val imageWidth: Float
    val imageHeight: Float

    // Determine the final dimensions of the image, fitting it within the container
    if (containerSize.aspectRatio > imageAspectRatio) {
        // Container is wider than the image (letterbox)
        imageHeight = containerSize.height.toFloat()
        imageWidth = imageHeight * imageAspectRatio
    } else {
        // Container is taller than or same as the image (pillarbox)
        imageWidth = containerSize.width.toFloat()
        imageHeight = imageWidth / imageAspectRatio
    }

    // Calculate the top-left corner to center the image
    val left = (containerSize.width - imageWidth) / 2f
    val top = (containerSize.height - imageHeight) / 2f

    return Rect(
        left = left,
        top = top,
        right = left + imageWidth,
        bottom = top + imageHeight
    )
}

val IntSize.aspectRatio: Float
    get() = if (height == 0) 0f else width.toFloat() / height

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
            onConfirmLatestEdition = {}
        )
    }
}