package com.jcpd.core_ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ParcheGradients {

    val BrandHero = Brush.linearGradient(
        colors = listOf(
            ParcheGreenDark,
            ParcheGreen,
            ParcheGreenMid
        )
    )

    val SoftHero = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF23834A),
            Color(0xFF6BCB7F)
        )
    )

    val CardHighlight = Brush.linearGradient(
        colors = listOf(
            ParcheGreen.copy(alpha = 0.95f),
            ParcheGreenMid.copy(alpha = 0.95f)
        )
    )
}