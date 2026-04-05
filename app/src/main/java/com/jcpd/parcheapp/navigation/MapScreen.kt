package com.jcpd.parcheapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jcpd.core_ui.theme.ParcheBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.ParcheWhite
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.core_ui.theme.TextPrimary

@Composable
fun MapScreen(
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
                    text = "Mapa",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Map,
                    contentDescription = null,
                    tint = TextMuted
                )

                Text(
                    text = "Vista de mapa próximamente",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextPrimary
                )

                Text(
                    text = "Aquí podrás ver partidos y actividades cerca de ti en el mapa.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted
                )
            }
        }
    }
}