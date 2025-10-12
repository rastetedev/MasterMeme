package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.annotation.DrawableRes
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
import com.raulastete.mastermeme.presentation.feature.create_meme.components.FontTypeConfiguration
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
        onUpdateFontSize = viewModel::updateFontSize,
        onUpdateFontColor = viewModel::updateFontColor,
        onUpdateFontType = viewModel::updateFontType,
        onUndoEdition = {},
        onRedoEdition = {},
        onAddTextBox = {},
        onSaveMeme = {})
}

@Composable
private fun CreateMemeScreenContent(
    @DrawableRes templateResourceId: Int,
    uiState: CreateMemeUiState,
    navigateBack: () -> Unit,
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
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
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

            /*Column(
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
            }*/

            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                FontTypeConfiguration(
                    modifier = Modifier.fillMaxWidth(),
                    selectedFontType = uiState.textState.fontType,
                    fontTypeList = uiState.fontTypes,
                    onTypeSelected = onUpdateFontType
                )
                TextOptions(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onDiscardChanges = {},
                    onConfirmChanges = {},
                    onEditFontFamily = {},
                    onEditFontSize = {},
                    onEditFontColor = {},
                    optionSelected = TextOption.FontType
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
            templateResourceId = 0,
            navigateBack = {},
            onUpdateFontSize = {},
            onUpdateFontColor = {},
            onUpdateFontType = {},
            onUndoEdition = {},
            onRedoEdition = {},
            onAddTextBox = {},
            onSaveMeme = {}
        )
    }
}