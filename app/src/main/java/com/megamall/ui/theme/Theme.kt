package com.megamall.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_on_primary,
    primaryContainer = md_theme_light_primary_container,
    onPrimaryContainer = md_theme_light_on_primary_container,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_on_secondary,
    secondaryContainer = md_theme_light_secondary_container,
    onSecondaryContainer = md_theme_light_on_secondary_container,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_on_tertiary,
    tertiaryContainer = md_theme_light_tertiary_container,
    onTertiaryContainer = md_theme_light_on_tertiary_container,
    error = md_theme_light_error,
    onError = md_theme_light_on_error,
    errorContainer = md_theme_light_error_container,
    onErrorContainer = md_theme_light_on_error_container,
    background = md_theme_light_background,
    onBackground = md_theme_light_on_background,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_on_surface,
    surfaceVariant = md_theme_light_surface_variant,
    onSurfaceVariant = md_theme_light_on_surface_variant,
    outline = md_theme_light_outline,
    outlineVariant = md_theme_light_outline_variant,
    scrim = md_theme_light_scrim,
    inverseSurface = md_theme_light_inverse_surface,
    inverseOnSurface = md_theme_light_inverse_on_surface,
    inversePrimary = md_theme_light_inverse_primary,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_on_primary,
    primaryContainer = md_theme_dark_primary_container,
    onPrimaryContainer = md_theme_dark_on_primary_container,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_on_secondary,
    secondaryContainer = md_theme_dark_secondary_container,
    onSecondaryContainer = md_theme_dark_on_secondary_container,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_on_tertiary,
    tertiaryContainer = md_theme_dark_tertiary_container,
    onTertiaryContainer = md_theme_dark_on_tertiary_container,
    error = md_theme_dark_error,
    onError = md_theme_dark_on_error,
    errorContainer = md_theme_dark_error_container,
    onErrorContainer = md_theme_dark_on_error_container,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_on_background,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_on_surface,
    surfaceVariant = md_theme_dark_surface_variant,
    onSurfaceVariant = md_theme_dark_on_surface_variant,
    outline = md_theme_dark_outline,
    outlineVariant = md_theme_dark_outline_variant,
    scrim = md_theme_dark_scrim,
    inverseSurface = md_theme_dark_inverse_surface,
    inverseOnSurface = md_theme_dark_inverse_on_surface,
    inversePrimary = md_theme_dark_inverse_primary,
)

@Composable
fun MegaMallTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MegaMallTypography,
        content = content
    )
}
