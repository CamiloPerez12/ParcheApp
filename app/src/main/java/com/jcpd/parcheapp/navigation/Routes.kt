package com.jcpd.parcheapp.navigation

object Routes {
    const val HOME = "home"
    const val EVENT_DETAIL = "event_detail"
    const val PROFILE = "profile"
    const val CHAT = "chat"
    const val MAP = "map"
    const val SEARCH = "search"

    const val EVENT_DETAIL_WITH_ARG = "event_detail/{eventId}"
    const val CHAT_WITH_ARG = "chat/{eventId}"

    fun eventDetail(eventId: String) = "event_detail/$eventId"
    fun chat(eventId: String) = "chat/$eventId"
}