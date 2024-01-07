package com.android.dailycat.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a tab item in a UI.
 *
 * This class encapsulates the properties of a tab item used in a tabbed layout, including its title,
 * icons for selected and unselected states, and the composable content for the tab page.
 *
 * @property title The title of the tab.
 * @property unSelectedIcon The icon to display when the tab is not selected.
 * @property selectedIcon The icon to display when the tab is selected.
 * @property goToPage A composable function that returns the content to be displayed when this tab is selected.
 */
data class TabItem(
    val title: String,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val goToPage: @Composable () -> Unit
)
