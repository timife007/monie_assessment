package com.timife.moniepointassessment.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Approval
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenRoute(val route: String, val label: String, val icon: ImageVector) {
    data object HomeScreen : ScreenRoute("home_screen", "Home", icon = Icons.Outlined.Home)
    data object ShipmentScreen :
        ScreenRoute("shipment_screen", "Shipment", icon = Icons.Outlined.Restore)

    data object SearchScreen : ScreenRoute("search_screen", "Search", Icons.Outlined.Search)
    data object CalculateScreen :
        ScreenRoute("calculate_screen", "Calculate", Icons.Outlined.Calculate)

    data object SuccessScreen : ScreenRoute("success_screen", "Success", Icons.Outlined.Approval)
    data object ProfileScreen : ScreenRoute("profile_screen", "Profile", Icons.Outlined.Person)
}