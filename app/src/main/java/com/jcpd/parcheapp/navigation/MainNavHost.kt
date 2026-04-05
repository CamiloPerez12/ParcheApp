package com.jcpd.parcheapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.jcpd.feature_chat.presentation.chat.ChatScreen
import com.jcpd.feature_home.presentation.home.HomeScreen
import com.jcpd.feature_event_detail.presentation.detail.EventDetailScreen
import com.jcpd.feature_profile.presentation.profile.ProfileScreen

@Composable
fun MainNavHost(
    startDestination: String = Routes.HOME
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.HOME) {
            HomeScreen(
                onEventClick = { eventId ->
                    navController.navigate(Routes.eventDetail(eventId))
                },
                onJoinClick = { eventId ->
                    navController.navigate(Routes.chat(eventId))
                },
                onNotificationClick = {
                },
                onSearchClick = {
                },
                onFilterClick = {
                },
                onMapClick = {
                }
            )
        }

        composable(
            route = Routes.EVENT_DETAIL_WITH_ARG,
            arguments = listOf(
                navArgument("eventId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId")

            EventDetailScreen(
                eventId = eventId ?: "",
                onBack = { navController.popBackStack() },
                onOpenChat = {
                    navController.navigate(Routes.chat(eventId ?: ""))
                }
            )
        }

        composable(
            route = Routes.CHAT_WITH_ARG,
            arguments = listOf(navArgument("eventId") {
                type = NavType.StringType
            })
        ) {
            val eventId = it.arguments?.getString("eventId").orEmpty()

            ChatScreen(
                eventId = eventId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}