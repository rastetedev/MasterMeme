package com.raulastete.mastermeme.feature.meme_list.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulastete.mastermeme.ui.theme.PurpleMedium2
import com.raulastete.mastermeme.ui.theme.White

@Composable
fun SearchTemplateMode(
    query: String,
    onCleanQuery: () -> Unit,
    onQueryChange: (String) -> Unit,
    onCloseSearchTemplate: () -> Unit,
    templatesQuantity: Int
) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCloseSearchTemplate) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                cursorBrush = SolidColor(PurpleMedium2),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = White
                ),
                modifier = Modifier
                    .weight(1f)
            ) { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        "Search input",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = White.copy(alpha = 0.3f)
                        ),
                    )
                }
                innerTextField()
            }
            IconButton(
                onClick = onCleanQuery,
                enabled = query.isNotBlank(),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor =  MaterialTheme.colorScheme.secondaryFixedDim,
                    disabledContentColor =  MaterialTheme.colorScheme.secondaryFixedDim.copy(alpha = 0.4f)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clean Search Query",
                )
            }
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
        )

        Spacer(Modifier.height(16.dp))

        AnimatedContent(templatesQuantity) { target ->

            val text = if (target == 0) "No memes found :C" else "$target memes"

            Text(
                text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

        }
    }
}