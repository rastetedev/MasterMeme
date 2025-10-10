package com.raulastete.mastermeme.feature.create_meme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreateMemeScreen(navigateBack: () -> Unit) {
    CreateMemeScreenContent(navigateBack = navigateBack)
}

@Composable
private fun CreateMemeScreenContent(navigateBack: () -> Unit) {
    //TODO: Add your content here
}

@Preview(showBackground = true)
@Composable
private fun CreateMemeScreenContentPreview() {
    MaterialTheme {
        CreateMemeScreenContent(navigateBack = {})
    }
}