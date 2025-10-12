package com.raulastete.mastermeme.presentation.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raulastete.mastermeme.domain.MemeFontType

data class MemeFontTypeUi(
    val displayName: String,
    val fontFamily: FontFamily = FontFamily.Default,
    val textStyle: TextStyle = TextStyle()
)

fun MemeFontTypeUi.toTextStyle(color: Color, fontSize: Float): TextStyle {
    return textStyle.merge(
        TextStyle(
            fontSize = fontSize.sp,
            color = color,
            fontFamily = fontFamily
        )
    )
}

fun MemeFontType.toUi(): MemeFontTypeUi =
    when (this) {
        MemeFontType.IMPACT -> MemeFontTypeUi(
            displayName = "Impact",
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        )

        MemeFontType.SYSTEM -> MemeFontTypeUi(
            displayName = "System",
            fontFamily = FontFamily.Default,
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal
            )
        )

        MemeFontType.ROBOTO_BOLD -> MemeFontTypeUi(
            displayName = "Roboto Bold",
            fontFamily = FontFamily.Default,
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )

        MemeFontType.COMIC -> MemeFontTypeUi(
            displayName = "Comic",
            fontFamily = FontFamily.SansSerif,
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.5.sp
            )
        )

        MemeFontType.STROKE -> MemeFontTypeUi(
            displayName = "Stroke",
            textStyle = TextStyle(
                drawStyle = Stroke(
                    width = 2f,
                    join = StrokeJoin.Round
                )
            )
        )

        MemeFontType.SHADOWED -> MemeFontTypeUi(
            displayName = "Shadowed",
            textStyle = TextStyle(
                shadow = Shadow(
                    offset = Offset(2f, 2f),
                    blurRadius = 3f
                )
            )
        )

        MemeFontType.OUTLINE -> MemeFontTypeUi(
            displayName = "Outline",
            textStyle = TextStyle(
                drawStyle = Stroke(
                    width = 3f,
                    join = StrokeJoin.Round
                )
            )
        )

        MemeFontType.RETRO -> MemeFontTypeUi(
            displayName = "Retro",
            textStyle = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
        )

        MemeFontType.HANDWRITTEN -> MemeFontTypeUi(
            displayName = "Handwritten",
            fontFamily = FontFamily.Cursive,
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp
            )
        )
    }

