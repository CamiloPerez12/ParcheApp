package com.jcpd.parcheapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.components.ParcheButton
import com.jcpd.core_ui.components.ParcheButtonStyle
import com.jcpd.core_ui.components.ParcheCard
import com.jcpd.core_ui.components.ParcheChip
import com.jcpd.core_ui.theme.ParcheBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun FilterScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ParcheBackground)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars),
            color = ParcheWhite,
            tonalElevation = 2.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.md,
                        vertical = spacing.md
                    )
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = TextPrimary
                    )
                }

                Text(
                    text = "Filtros",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            ParcheCard {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.md)
                ) {
                    Text(
                        text = "Deporte",
                        style = MaterialTheme.typography.headlineSmall,
                        color = TextPrimary
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        ParcheChip(text = "Fútbol", selected = true, onClick = {})
                        ParcheChip(text = "Tenis", selected = false, onClick = {})
                        ParcheChip(text = "Basket", selected = false, onClick = {})
                        ParcheChip(text = "Social", selected = false, onClick = {})
                    }
                }
            }

            ParcheCard {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.md)
                ) {
                    Text(
                        text = "Nivel",
                        style = MaterialTheme.typography.headlineSmall,
                        color = TextPrimary
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                        verticalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        ParcheChip(text = "Principiante", selected = false, onClick = {})
                        ParcheChip(text = "Intermedio", selected = true, onClick = {})
                        ParcheChip(text = "Avanzado", selected = false, onClick = {})
                    }
                }
            }

            ParcheCard {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm)
                ) {
                    RowTitle(title = "Distancia")
                    Text(
                        text = "Hasta 5 km",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextMuted
                    )
                }
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.md),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = null,
                        tint = TextMuted
                    )

                    Text(
                        text = "Pantalla de filtros próximamente",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextMuted
                    )
                }
            }

            ParcheButton(
                text = "Aplicar filtros",
                onClick = onBack,
                style = ParcheButtonStyle.Primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun RowTitle(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = TextPrimary
    )
}