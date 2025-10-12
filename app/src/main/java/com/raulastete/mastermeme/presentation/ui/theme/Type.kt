package com.raulastete.mastermeme.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raulastete.mastermeme.R

val ManropeFont = FontFamily(
    Font(resId = R.font.manrope_regular, FontWeight.Normal),
    Font(resId = R.font.manrope_medium, FontWeight.Medium),
    Font(resId = R.font.manrope_semibold, FontWeight.SemiBold),
    Font(resId = R.font.manrope_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    //Button
    labelLarge = TextStyle(
        fontFamily = ManropeFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
)