package com.raulastete.mastermeme.presentation.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

enum class MemeFontTypeUi(
    val displayName: String,
    val fontFamily: FontFamily = FontFamily.Default,
    val textStyle: TextStyle = TextStyle()
) {

    SYSTEM(
        displayName = "System",
        fontFamily = FontFamily.Default,
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal
        )
    ),

    COMIC(
        displayName = "Comic",
        fontFamily = FontFamily.SansSerif,
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp
        )
    ),

    STROKE(
        displayName = "Stroke",
        textStyle = TextStyle(
            drawStyle = Stroke(
                width = 2f,
                join = StrokeJoin.Round
            )
        )
    ),

    SHADOWED(
        displayName = "Shadowed",
        textStyle = TextStyle(
            shadow = Shadow(
                offset = Offset(2f, 2f),
                blurRadius = 3f
            )
        )
    ),

    HANDWRITTEN(
        displayName = "Handwritten",
        fontFamily = FontFamily.Cursive,
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            letterSpacing = 1.sp
        )
    )
}

