package com.raulastete.mastermeme.feature.meme_list

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MemeListScreen(navigateToCreateMeme: () -> Unit) {
    MemeListScreenContent(navigateToCreateMeme = navigateToCreateMeme)
}

@Composable
private fun MemeListScreenContent(navigateToCreateMeme: () -> Unit) {
    //TODO: Add your content here
}

@Preview(showBackground = true)
@Composable
private fun MemeListScreenContentPreview() {
    MaterialTheme {
        MemeListScreenContent(navigateToCreateMeme = {})
    }
}
