package com.timife.moniepointassessment.presentation.shipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.domain.Shipment
import com.timife.moniepointassessment.domain.Status
import com.timife.moniepointassessment.domain.getIcon
import com.timife.moniepointassessment.domain.shipments
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Purple

@Composable
fun ShipmentItem(modifier: Modifier, shipment: Shipment) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatusChip(
                text = when (shipment.status) {
                    Status.IN_PROGRESS -> shipment.status.status.lowercase().split(" ")
                        .joinToString("-")

                    Status.PENDING -> shipment.status.status.slice(0..7).lowercase()
                    else -> shipment.status.status.lowercase()
                },
                color = shipment.color,
                icon = getIcon(shipment.status)
            )
            Row {
                Column {
                    Text(
                        text = shipment.note,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Text(
                        text = shipment.message,
                        modifier = Modifier.fillMaxWidth(0.75f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.package_icon),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = shipment.cost,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Purple
                    )
                )
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(7.dp)
                )
                Text(
                    text = shipment.date,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatusChip(text: String, color: Color, icon: ImageVector) {

    Chip(
        modifier = Modifier.padding(end = 4.dp),
        onClick = {},
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = color)
        },
    ) {
        Text(text, style = MaterialTheme.typography.bodySmall.copy(color = color))
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentItemPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            ShipmentItem(modifier = Modifier, shipments[0])
        }
    }
}