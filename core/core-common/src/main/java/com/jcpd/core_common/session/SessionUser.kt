package com.jcpd.core_common.session

data class SessionUser(
    val id: String,
    val name: String,
    val rating: Double,
    val isVerified: Boolean
)