package com.timife.moniepointassessment.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = LightPurple,
    tertiary = Orange,
    surface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = LightPurple,
    tertiary = Orange,
    onPrimary = White,
    background = LighterGrey,
    onBackground = Color.Black,
    surface = Color.White,
    surfaceVariant = Color.LightGray

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MoniepointAssessmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    val configuration = LocalConfiguration.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = customTypography,
        ){
            content()
        }
    }

}

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}
private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}