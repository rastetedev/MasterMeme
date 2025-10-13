package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontColorConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontSizeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontTypeConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.MainOptions
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOption
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
    Scaffold(
        topBar = {
            NavigationalTopBar(
                title = "New meme",
                onNavigateBack = navigateBack
            )
        }
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            AsyncImage(
                model = templateResourceId,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                val currentMode = uiState.editModeState.mode

                if (currentMode is EditMode.InEdition) {

                    AnimatedContent(
                        targetState = currentMode.textOption,
                        label = "TextOptionAnimation"
                    ) { option ->
                        when (option) {
                            TextOption.FontType -> FontTypeConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                selectedFontType = uiState.textStates.first().fontType,
                                fontTypeList = uiState.fontTypes,
                                onTypeSelected = onUpdateFontType
                            )

                            TextOption.FontSize -> FontSizeConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                fontSizeFloat = uiState.textStates.first().fontSize,
                                onFontSizeFloatChange = onUpdateFontSize
                            )

                            TextOption.FontColor -> FontColorConfiguration(
                                modifier = Modifier.fillMaxWidth(),
                                selectedColor = uiState.textStates.first().fontColor,
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
                        optionSelected = currentMode.textOption
                    )

                } else {
                    MainOptions(
                        modifier = Modifier.fillMaxWidth(),
                        canUndo = uiState.editModeState.canUndo,
                        canRedo = uiState.editModeState.canRedo,
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