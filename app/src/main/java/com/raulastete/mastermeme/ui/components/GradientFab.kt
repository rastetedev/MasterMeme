package com.raulastete.mastermeme.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Floating Action Button con un fondo de gradiente.
 */
@Composable
fun GradientFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFEADDFF),
                            Color(0xFFD0BCFE)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                modifier = Modifier.size(24.dp),
                contentDescription = "Add new item",
                tint = Color(0xFF21005D)
            )
        }
    }
}