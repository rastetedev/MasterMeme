package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontColorConfiguration
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOption
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOptions
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.ui.components.NavigationalTopBar

@Composable
fun CreateMemeScreen(
    viewModel: CreateMemeViewModel = viewModel(),
    navigateBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateMemeScreenContent(
        uiState = uiState,
        navigateBack = navigateBack,
        onUpdateFontSize = viewModel::updateFontSize,
        onUpdateFontColor = viewModel::updateFontColor,
        onUndoEdition = {},
        onRedoEdition = {},
        onAddTextBox = {},
        onSaveMeme = {})
}

@Composable
private fun CreateMemeScreenContent(
    uiState: CreateMemeUiState,
    navigateBack: () -> Unit,
    onUpdateFontSize: (Float) -> Unit,
    onUpdateFontColor: (MemeFontColorUi) -> Unit,
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
            /*MainOptions(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onUndoEdition = onUndoEdition,
                onRedoEdition = onRedoEdition,
                onAddTextBox = onAddTextBox,
                onSaveMeme = onSaveMeme
            )*/

            /*Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                FontSizeConfiguration(
                    modifier = Modifier.fillMaxWidth(),
                    fontSizeFloat = uiState.textState.fontSize,
                    onFontSizeFloatChange = onUpdateFontSize
                )
                TextOptions(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onDiscardChanges = {},
                    onConfirmChanges = {},
                    onEditFontFamily = {},
                    onEditFontSize = {},
                    onEditFontColor = {},
                    optionSelected = TextOption.FontSize
                )
            }*/

            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                FontColorConfiguration(
                    modifier = Modifier.fillMaxWidth(),
                    selectedColor = uiState.textState.fontColor,
                    fontColorList = uiState.fontColors,
                    onColorSelected = onUpdateFontColor
                )
                TextOptions(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onDiscardChanges = {},
                    onConfirmChanges = {},
                    onEditFontFamily = {},
                    onEditFontSize = {},
                    onEditFontColor = {},
                    optionSelected = TextOption.FontColor
                )
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
            navigateBack = {},
            onUpdateFontSize = {},
            onUpdateFontColor = {},
            onUndoEdition = {},
            onRedoEdition = {},
            onAddTextBox = {},
            onSaveMeme = {}
        )
    }
}