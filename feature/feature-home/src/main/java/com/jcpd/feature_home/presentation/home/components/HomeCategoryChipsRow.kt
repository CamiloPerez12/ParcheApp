package com.jcpd.feature_home.presentation.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jcpd.core_ui.components.ParcheChip
import com.jcpd.core_ui.theme.ParcheThemeTokens
import com.jcpd.feature_home.presentation.home.HomeCategoryUi

@Composable
fun HomeCategoryChipsRow(
    categories: List<HomeCategoryUi>,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = ParcheThemeTokens.spacing

    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        categories.forEach { category ->
            ParcheChip(
                text = stringResource(category.labelRes),
                selected = category.isSelected,
                onClick = { onCategorySelected(category.id) }
            )
        }
    }
}