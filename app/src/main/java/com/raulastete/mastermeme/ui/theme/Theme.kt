package com.raulastete.mastermeme.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    surfaceContainerHigh = SurfaceContainerHighDark,
    outline = Outline,
    primary = LightPrimary,
    secondaryFixedDim = SecondaryFixedDim,
    onSurface = OnSurface,
    primaryContainer = PrimaryContainer,
    error = Error,
    onPrimaryFixed = OnPrimaryFixed,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
)

@Composable
fun MasterMemeTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}