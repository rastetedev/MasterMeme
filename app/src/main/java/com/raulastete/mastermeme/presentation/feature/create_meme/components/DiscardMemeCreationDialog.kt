package com.raulastete.mastermeme.presentation.feature.create_meme.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.ui.theme.White

@Composable
fun DiscardMemeCreationDialog(
    onDismiss: () -> Unit = { },
    onConfirm: () -> Unit = { },
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.dialog_discard_meme_creation_title),
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.onSurface)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.dialog_discard_meme_creation_message),
                style = MaterialTheme.typography.bodyMedium.copy(color = White)
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.surfaceDim
                )
            ) {
                Text(
                    text = stringResource(R.string.dialog_discard_meme_creation_confirm_button),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surfaceDim)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.surfaceDim

                )
            ) {
                Text(
                    text = stringResource(R.string.dialog_discard_meme_creation_cancel_button),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surfaceDim)
                )
            }
        }
    )
}