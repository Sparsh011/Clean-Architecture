package com.example.cleanarchitecturedictionaryapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object StackOverflow: BottomBarScreen(
        route = "StackOverflow",
        title = "StackOverflow",
        icon = Icons.Default.Home
    )

    object Dictionary: BottomBarScreen(
        route = "Dictionary",
        title = "Dictionary",
        icon = Icons.Default.List
    )
}