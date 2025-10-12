package com.raulastete.mastermeme.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.tooling.preview.Preview

/**
 * Estado normal: solo muestra un título.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(title: String) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.headlineLarge) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}

/**
 * Estado con navegación: muestra un botón de retroceso y un título centrado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationalTopBar(title: String, onNavigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.headlineLarge) },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}

/**
 * Estado con acciones: muestra un título y un indicador de ordenación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsTopBar(title: String, sortIndicator: String, onSortClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            TextButton(onClick = onSortClick) {
                Text(text = sortIndicator)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
    )
}

/**
 * Estado de selección: muestra un botón para salir, el recuento de seleccionados y acciones.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionTopBar(
    selectedCount: Int,
    onExitSelectionMode: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "$selectedCount selected") },
        navigationIcon = {
            IconButton(onClick = onExitSelectionMode) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Exit selection mode"
                )
            }
        },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share selected items"
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete selected items"
                    )
                }
            }
        }
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
    ItemsTopBar(title = "My Memes", sortIndicator = "Newest first", onSortClick = {})
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
