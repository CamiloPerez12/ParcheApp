package com.jcpd.parcheapp.navigation

sealed class NavDestination(val route: String) {

    object Home : NavDestination(Routes.HOME)

    object EventDetail : NavDestination(Routes.EVENT_DETAIL_WITH_ARG) {
        const val ARG_EVENT_ID = "eventId"
    }

    object Profile : NavDestination(Routes.PROFILE)

    object Chat : NavDestination(Routes.CHAT_WITH_ARG) {
        const val ARG_EVENT_ID = "eventId"
    }
}