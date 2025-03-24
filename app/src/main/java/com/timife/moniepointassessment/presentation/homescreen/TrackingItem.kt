package com.timife.moniepointassessment.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.ui.theme.Apricot
import com.timife.moniepointassessment.ui.theme.FadeGreen
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Orange

@Composable
fun TrackingItem(modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
        ) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        text = "Shipment Number", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "NEJ20089934122231",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.7f))
                Image(
                    painter = painterResource(id = R.drawable.fork_lift),
                    modifier = Modifier.size(56.dp),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier
                .height(15.dp)
                .background(Color.LightGray))
            Divider()
            Row(modifier = Modifier.padding(top = 15.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.box_delivery_package_9_svgrepo_com),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Apricot) // Clips the image to a circle shape
                        .clickable { /* Handle click event */ }
                        .size(35.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Sender", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                    Text(text = "Atlanta, 5243", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                Column (verticalArrangement = Arrangement.spacedBy(4.dp)){
                    Text(text = "Time", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        Icon(imageVector = Icons.Default.Circle, tint = Color.Green, modifier = Modifier.size(5.dp), contentDescription = null)
                        Text(text = "2 days - 3 days", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.padding(top = 15.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.box_delivery_package_7_svgrepo_com),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(FadeGreen) // Clips the image to a circle shape
                        .clickable { /* Handle click event */ }
                        .size(35.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Receiver", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                    Text(text = "Chicago, 6342", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                Column (verticalArrangement = Arrangement.spacedBy(4.dp)){
                    Text(text = "Status", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                    Text(text = "Waiting to collect", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider()
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(imageVector = Icons.Outlined.Add, tint = Orange, contentDescription = null)
                    Text(text = "Add Stop", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = Orange))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackingItemScreenPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            TrackingItem(modifier = Modifier)
        }
    }
}