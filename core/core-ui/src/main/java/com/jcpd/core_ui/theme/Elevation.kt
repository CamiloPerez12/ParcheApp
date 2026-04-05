package com.jcpd.core_ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ParcheElevation(
    val card: Dp = 3.dp,
    val modal: Dp = 6.dp,
    val fab: Dp = 8.dp,
    val pressed: Dp = 1.dp
)

val LocalParcheElevation = staticCompositionLocalOf { ParcheElevation() }