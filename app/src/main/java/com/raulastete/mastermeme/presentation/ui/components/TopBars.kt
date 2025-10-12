package com.raulastete.mastermeme.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.feature.meme_list.SortingMode
import com.raulastete.mastermeme.presentation.feature.meme_list.toText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationalTopBar(title: String, onNavigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate back",
                    tint = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsTopBar(title: String, sortingMode: SortingMode, onSortClick: (SortingMode) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        actions = {
            TextButton(onClick = {  }) {
                Text(text = sortingMode.toText())
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionTopBar(
    selectedCount: Int,
    onExitSelectionMode: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "$selectedCount selected",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = onExitSelectionMode) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.close),
                    contentDescription = "Exit selection mode",
                    tint = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
        },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.share),
                        contentDescription = "Share selected items",
                        tint = MaterialTheme.colorScheme.secondaryFixedDim
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.delete),
                        contentDescription = "Delete selected items",
                        tint = MaterialTheme.colorScheme.secondaryFixedDim
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}


@Preview(showBackground = true, name = "Normal TopBar")
@Composable
private fun NormalTopBarPreview() {
    NormalTopBar(title = "My Memes")
}

@Preview(showBackground = true, name = "Navigational TopBar")
@Composable
private fun NavigationalTopBarPreview() {
    NavigationalTopBar(title = "Meme Details", onNavigateBack = {})
}

@Preview(showBackground = true, name = "Items TopBar")
@Composable
private fun ItemsTopBarPreview() {
    ItemsTopBar(title = "My Memes", sortingMode = SortingMode.NEWEST_FIRST, onSortClick = {})
}

@Preview(showBackground = true, name = "Selection TopBar")
@Composable
private fun SelectionTopBarPreview() {
    SelectionTopBar(
        selectedCount = 3,
        onExitSelectionMode = {},
        onShareClick = {},
        onDeleteClick = {}
    )
}
