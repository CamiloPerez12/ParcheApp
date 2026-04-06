package com.jcpd.feature_home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.core_ui.components.ParcheButton
import com.jcpd.core_ui.components.ParcheButtonStyle
import com.jcpd.core_ui.components.ParcheSearchBar
import com.jcpd.core_ui.theme.ParcheBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.feature_home.R
import com.jcpd.feature_home.presentation.home.components.HomeCategoryChipsRow
import com.jcpd.feature_home.presentation.home.components.HomeEventList
import com.jcpd.feature_home.presentation.home.components.HomeHeroSection
import com.jcpd.feature_home.presentation.home.components.HomeQuickStatsRow
import com.jcpd.feature_home.presentation.home.mapper.EventCardMapper

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
    onMapClick: () -> Unit = {},
    onEventClick: (String) -> Unit = {},
    onJoinClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        modifier = modifier,
        onRetry = viewModel::loadHome,
        onNotificationClick = onNotificationClick,
        onProfileClick = onProfileClick,
        onSearchClick = onSearchClick,
        onFilterClick = onFilterClick,
        onMapClick = onMapClick,
        onCategorySelected = viewModel::onCategorySelected,
        onEventClick = onEventClick,
        onJoinClick = onJoinClick
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit,
    onMapClick: () -> Unit,
    onCategorySelected: (String) -> Unit,
    onEventClick: (String) -> Unit,
    onJoinClick: (String) -> Unit
) {
    val spacing = ParcheThemeTokens.spacing
    val context = LocalContext.current

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessageRes != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground)
                    .padding(spacing.xl),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(uiState.errorMessageRes),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    ParcheButton(
                        text = stringResource(R.string.home_retry),
                        onClick = onRetry,
                        style = ParcheButtonStyle.Primary
                    )
                }
            }
        }

        else -> {
            val eventCards = uiState.events.map { event ->
                EventCardMapper.toUiModel(
                    context = context,
                    event = event,
                    onJoinClick = { onJoinClick(event.id) },
                    onCardClick = { onEventClick(event.id) }
                )
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground),
                verticalArrangement = Arrangement.spacedBy(spacing.lg),
                contentPadding = PaddingValues(bottom = spacing.huge)
            ) {
                item {
                    HomeHeroSection(
                        greeting = uiState.greetingRes?.let { stringResource(it) }.orEmpty(),
                        title = uiState.titleRes?.let { stringResource(it) }.orEmpty(),
                        locationLabel = uiState.locationLabel,
                        unreadNotificationsCount = uiState.unreadNotificationsCount,
                        onNotificationClick = onNotificationClick,
                        onProfileClick = onProfileClick,
                        notificationIcon = Icons.Outlined.Notifications
                    )
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(spacing.lg)
                    ) {
                        ParcheSearchBar(
                            placeholder = uiState.searchPlaceholderRes?.let { stringResource(it) }
                                .orEmpty(),
                            onClick = onSearchClick,
                            onFilterClick = onFilterClick
                        )

                        if (uiState.quickStats.isNotEmpty()) {
                            HomeQuickStatsRow(stats = uiState.quickStats)
                        }

                        if (uiState.categories.isNotEmpty()) {
                            HomeCategoryChipsRow(
                                categories = uiState.categories,
                                onCategorySelected = onCategorySelected
                            )
                        }

                        if (uiState.showMapShortcut && uiState.mapShortcutLabelRes != null) {
                            ParcheButton(
                                text = "",
                                onClick = onMapClick,
                                style = ParcheButtonStyle.Secondary,
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Place,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = stringResource(uiState.mapShortcutLabelRes))
                                }
                            )
                        }
                    }
                }

                if (uiState.isEmpty) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = spacing.lg, vertical = spacing.xl),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(spacing.md)
                            ) {
                                Text(
                                    text = stringResource(R.string.home_empty_title),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Text(
                                    text = stringResource(R.string.home_empty_subtitle),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Text(
                            text = stringResource(R.string.home_nearby_events_title),
                            modifier = Modifier.padding(horizontal = spacing.lg),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }

                    item {
                        HomeEventList(
                            events = eventCards,
                            contentPadding = PaddingValues(horizontal = spacing.lg)
                        )
                    }
                }
            }
        }
    }
}