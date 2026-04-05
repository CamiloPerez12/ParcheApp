package com.jcpd.feature_home.domain.usecase

import com.jcpd.feature_home.domain.repository.HomeRepository

class GetSelectedLocationUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): String {
        return repository.getSelectedLocation()
    }
}