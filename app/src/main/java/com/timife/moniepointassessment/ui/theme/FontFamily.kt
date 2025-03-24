package com.timife.moniepointassessment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.timife.moniepointassessment.R

private val mrLight = Font(R.font.inter_light, FontWeight.Light)
private val mrRegular = Font(R.font.inter_regular, FontWeight.Normal)
private val mrMedium = Font(R.font.inter_medium, FontWeight.Medium)
private val mrBold = Font(R.font.inter_medium, FontWeight.Bold)

// Create a font family to use in TextStyles
val manropeFontFamily = FontFamily(mrLight, mrRegular, mrMedium, mrBold)

// Use the font family to define a custom typography
val customTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineLarge = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 64.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 44.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleLarge = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),
    titleSmall = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = manropeFontFamily,
    ),
    bodyMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),
    displaySmall = TextStyle(
        fontFamily = manropeFontFamily,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Light,
    )
)