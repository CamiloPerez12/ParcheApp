package com.jcpd.core_common.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeUserSessionRepository : UserSessionRepository {

    private val _currentUser = MutableStateFlow(
        SessionUser(
            id = "current_user",
            name = "You",
            rating = 4.8,
            isVerified = true
        )
    )

    override val currentUser: StateFlow<SessionUser> = _currentUser
}