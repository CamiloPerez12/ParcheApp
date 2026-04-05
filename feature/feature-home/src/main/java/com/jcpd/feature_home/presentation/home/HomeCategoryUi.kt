package com.jcpd.feature_home.presentation.home

import androidx.annotation.StringRes

data class HomeCategoryUi(
    val id: String,
    @StringRes val labelRes: Int,
    val isSelected: Boolean = false
)