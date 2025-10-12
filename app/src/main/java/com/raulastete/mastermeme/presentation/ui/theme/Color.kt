package com.raulastete.mastermeme.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val SurfaceContainerLowest = Color(0xFF0F0D13)
val SurfaceContainerLow = Color(0xFF1D1B20)
val SurfaceContainer = Color(0xFF211F26)
val SurfaceContainerHighDark = Color(0xFF2B2930)
val Outline = Color(0XFF79747E)
val LightPrimary = Color(0XFF65558F)
val SecondaryFixedDim = Color(0xFFCCC2DC)
val OnSurface = Color(0xFFE6E0E9)
val PrimaryContainer = Color(0xFFEADDFF)
val SurfaceContainerHighLight = Color(0xFFECE6F0)
val Error = Color(0xFFB3261E)
val OnPrimaryFixed = Color(0xFF21005D)

val PurpleMedium1 = Color(0xFFD0BCFE)
val PurpleLight2 = Color(0xFFE0D0FA)
val PurpleMedium2 = Color(0xFFAD90F1)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val ButtonGradientDefault = Brush.linearGradient(
    colors = listOf(
        PrimaryContainer,
        PurpleMedium1
    )
)

val ButtonGradientPressed = Brush.linearGradient(
    colors = listOf(
        PurpleLight2,
        PurpleMedium2
    )
)