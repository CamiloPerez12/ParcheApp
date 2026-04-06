package com.jcpd.core_common.session

import kotlinx.coroutines.flow.StateFlow

interface UserSessionRepository {
    val currentUser: StateFlow<SessionUser>
}