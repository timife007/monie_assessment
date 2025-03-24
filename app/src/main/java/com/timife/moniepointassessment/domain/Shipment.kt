package com.timife.moniepointassessment.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.AvTimer
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.timife.moniepointassessment.ui.theme.Orange
import com.timife.moniepointassessment.ui.theme.Purple
import com.timife.moniepointassessment.ui.theme.TagGreen

data class Shipment(
    val status: Status,
    val note: String,
    val message: String,
    val cost: String,
    val date: String,
    val color: Color
)

enum class Status(val status: String) {
    ALL("All"),
    COMPLETED("Completed"),
    IN_PROGRESS("In progress"),
    PENDING("Pending order"),
    LOADING("Loading"),
    CANCELLED("Cancelled")
}

val shipments = listOf(
    Shipment(
        Status.IN_PROGRESS,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$1400 USD",
        "Sep 20 2024",
        TagGreen
    ),
    Shipment(
        Status.COMPLETED,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, has been completed!",
        "$3000 USD",
        "Sep 20 2024",
        Purple
    ),
    Shipment(
        Status.PENDING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is pending!",
        "$1400 USD",
        "Sep 20 2024",
        Orange
    ),
    Shipment(
        Status.LOADING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$300 USD",
        "Sep 20 2024",
        Color.Blue
    ),
    Shipment(
        Status.IN_PROGRESS,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$1400 USD",
        "Sep 20 2024",
        TagGreen
    ),
    Shipment(
        Status.PENDING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is pending!",
        "$650 USD",
        "Sep 20 2024",
        Orange
    ),
    Shipment(
        Status.IN_PROGRESS,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$1400 USD",
        "Sep 20 2024",
        TagGreen
    ),
    Shipment(
        Status.COMPLETED,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, has been completed!",
        "$850 USD",
        "Sep 20 2024",
        Purple
    ),
    Shipment(
        Status.LOADING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$1400 USD",
        "Sep 20 2024",
        Color.Blue
    ),
    Shipment(
        Status.PENDING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is pending!",
        "$3000 USD",
        "Sep 20 2024",
        Orange
    ),
    Shipment(
        Status.COMPLETED,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, has been completed!",
        "$3000 USD",
        "Sep 20 2024",
        Purple
    ),
    Shipment(
        Status.PENDING,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is pending!",
        "$3000 USD",
        "Sep 20 2024",
        Orange
    ),
    Shipment(
        Status.IN_PROGRESS,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, is arriving today!",
        "$1500 USD",
        "Sep 20 2024",
        TagGreen
    ),

    Shipment(
        Status.CANCELLED,
        "Arriving today!",
        "Your delivery #NEJ20089934 \nfrom Atlanta, has been cancelled!",
        "$3000 USD",
        "Sep 20 2024",
        Color.Red
    ),
)

fun getIcon(status: Status): ImageVector {
    return when (status) {
        Status.IN_PROGRESS -> {
            Icons.Outlined.Autorenew
        }

        Status.PENDING -> {
            Icons.Outlined.Restore
        }

        Status.COMPLETED -> {
            Icons.Outlined.CheckCircleOutline
        }

        Status.CANCELLED -> {
            Icons.Outlined.Cancel
        }

        else -> {
            Icons.Outlined.AvTimer
        }
    }
}