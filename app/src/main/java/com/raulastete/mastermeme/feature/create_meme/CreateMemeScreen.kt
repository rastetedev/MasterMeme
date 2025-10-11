package com.raulastete.mastermeme.feature.create_meme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raulastete.mastermeme.feature.create_meme.components.FontSizeConfiguration
import com.raulastete.mastermeme.feature.create_meme.components.MainOptions
import com.raulastete.mastermeme.feature.create_meme.components.TextOption
import com.raulastete.mastermeme.feature.create_meme.components.TextOptions
import com.raulastete.mastermeme.ui.components.NavigationalTopBar

@Composable
fun CreateMemeScreen(navigateBack: () -> Unit) {
    CreateMemeScreenContent(
        navigateBack = navigateBack,
        onUndoEdition = {}, onRedoEdition = {}, onAddTextBox = {}, onSaveMeme = {})
}

@Composable
private fun CreateMemeScreenContent(
    navigateBack: () -> Unit,
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

            var fonSizeFloat = remember { mutableStateOf(0.5f) }
            Column(Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
                FontSizeConfiguration(
                    modifier = Modifier.fillMaxWidth(),
                    fontSizeFloat = fonSizeFloat.value,
                    onFontSizeFloatChange = {
                        fonSizeFloat.value = it
                    }
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
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun CreateMemeScreenContentPreview() {
    MaterialTheme {
        CreateMemeScreenContent(
            navigateBack = {},
            onUndoEdition = {},
            onRedoEdition = {},
            onAddTextBox = {},
            onSaveMeme = {})
    }
}