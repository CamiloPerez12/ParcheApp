package com.jcpd.core_ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val ParcheLightColorScheme = lightColorScheme(
    primary = ParcheGreen,
    onPrimary = ParcheWhite,
    primaryContainer = ParcheGreenLight,
    onPrimaryContainer = ParcheGreenDark,

    secondary = ParcheGreenMid,
    onSecondary = ParcheWhite,
    secondaryContainer = Gray100,
    onSecondaryContainer = TextPrimary,

    tertiary = InfoBlue,
    onTertiary = ParcheWhite,

    background = ParcheBackground,
    onBackground = TextPrimary,

    surface = ParcheWhite,
    onSurface = TextPrimary,

    surfaceVariant = Gray100,
    onSurfaceVariant = TextSecondary,

    outline = Gray300,
    outlineVariant = Gray200,

    error = ErrorRed,
    onError = ParcheWhite,
    errorContainer = ErrorContainer,
    onErrorContainer = ErrorRed
)

@Composable
fun ParcheTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalParcheSpacing provides ParcheSpacing(),
        LocalParcheElevation provides ParcheElevation()
    ) {
        MaterialTheme(
            colorScheme = ParcheLightColorScheme,
            typography = ParcheTypography,
            shapes = ParcheShapes,
            content = content
        )
    }
}

object ParcheThemeTokens {
    val spacing: ParcheSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalParcheSpacing.current

    val elevation: ParcheElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalParcheElevation.current
}