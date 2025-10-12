package com.raulastete.mastermeme.presentation.feature.meme_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.ui.theme.White

@Composable
fun ModalDefaultHeader(
    onOpenSearchTemplate: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.default_modal_header_title),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(onClick = onOpenSearchTemplate) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
        }
        Text(
            stringResource(R.string.default_modal_header_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = White
        )
    }
}