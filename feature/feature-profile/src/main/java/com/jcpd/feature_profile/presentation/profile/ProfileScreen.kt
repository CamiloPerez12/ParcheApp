package com.jcpd.feature_profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.core_ui.theme.ParcheBackground
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.core_ui.theme.TextMuted
import com.jcpd.feature_profile.presentation.profile.components.ProfileHeader
import com.jcpd.feature_profile.presentation.profile.components.ProfileInfoCard
import com.jcpd.feature_profile.presentation.profile.components.ProfileInterestChips
import com.jcpd.feature_profile.presentation.profile.components.ProfileStatsRow

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val spacing = ParcheThemeTokens.spacing

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

        uiState.errorMessage != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.errorMessage.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextMuted
                )
            }
        }

        uiState.profile != null -> {
            val profile = uiState.profile

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(ParcheBackground),
                contentPadding = PaddingValues(bottom = spacing.xl),
                verticalArrangement = Arrangement.spacedBy(spacing.lg)
            ) {
                item {
                    ProfileHeader(
                        fullName = profile?.fullName ?: "",
                        username = profile?.username ?: "",
                        city = profile?.city ?: "",
                        onBack = onBack
                    )
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(spacing.lg)
                    ) {
                        ProfileStatsRow(
                            rating = profile?.rating ?: "",
                            eventsJoined = profile?.eventsJoined ?: "",
                            reliability = profile?.reliability ?: ""
                        )

                        ProfileInfoCard(
                            title = "Bio",
                            content = profile?.bio ?: ""
                        )

                        ProfileInterestChips(
                            interests = profile?.preferredSports ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}