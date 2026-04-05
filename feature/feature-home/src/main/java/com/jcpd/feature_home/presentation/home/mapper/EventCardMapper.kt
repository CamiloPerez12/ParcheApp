package com.jcpd.feature_home.presentation.home.mapper

import android.content.Context
import com.jcpd.core_ui.components.EventCardState
import com.jcpd.core_ui.components.EventCardUiModel
import com.jcpd.feature_home.R
import com.jcpd.feature_home.presentation.home.HomeEventUi
import java.text.DecimalFormat

object EventCardMapper {

    fun toUiModel(
        context: Context,
        event: HomeEventUi,
        onJoinClick: (() -> Unit)? = null,
        onCardClick: (() -> Unit)? = null
    ): EventCardUiModel {
        return EventCardUiModel(
            id = event.id,
            title = event.title,
            locationName = event.locationName,
            distanceText = formatDistance(context, event.distanceKm),
            dateText = event.dateLabel,
            playersText = formatPlayers(context, event.joinedPlayers, event.totalPlayers),
            levelText = event.levelLabel,
            priceText = event.priceLabel,
            statusText = event.statusLabel,
            statusType = event.statusType,
            ratingText = formatRating(event.rating),
            spotsText = formatRemainingSpots(context, event.remainingSpots),
            ctaText = resolveCtaText(context, event.state),
            state = event.state,
            sportType = event.sportType,
            participantInitials = event.participantInitials,
            onJoinClick = onJoinClick,
            onCardClick = onCardClick
        )
    }

    private fun resolveCtaText(
        context: Context,
        state: EventCardState
    ): String {
        return when (state) {
            EventCardState.Default -> context.getString(R.string.event_join)
            EventCardState.Joined -> context.getString(R.string.event_joined)
            EventCardState.Full -> context.getString(R.string.event_full)
        }
    }

    private fun formatPlayers(
        context: Context,
        joinedPlayers: Int,
        totalPlayers: Int
    ): String {
        return context.getString(
            R.string.event_players_format,
            joinedPlayers,
            totalPlayers
        )
    }

    private fun formatRemainingSpots(
        context: Context,
        remainingSpots: Int
    ): String {
        return context.resources.getQuantityString(
            R.plurals.event_remaining_spots,
            remainingSpots,
            remainingSpots
        )
    }

    private fun formatDistance(
        context: Context,
        distanceKm: Double
    ): String {
        val formatter = DecimalFormat("#.#")
        return context.getString(
            R.string.event_distance_km,
            formatter.format(distanceKm)
        )
    }

    private fun formatRating(
        rating: Double
    ): String {
        return DecimalFormat("#.#").format(rating)
    }
}