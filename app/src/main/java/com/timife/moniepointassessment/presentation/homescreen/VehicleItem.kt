package com.timife.moniepointassessment.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.domain.Vehicle
import com.timife.moniepointassessment.domain.vehicles
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme

@Composable
fun VehicleItem(
    modifier: Modifier,
    vehicle: Vehicle,
    height: Dp,
) {
    val width = LocalConfiguration.current.screenWidthDp
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .height(height)
            .width((width / 2.8).dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Image(
                painter = painterResource(vehicle.image),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxSize()
                    .offset(
                        x = if(vehicle.name == "Air freight") (+83).dp else (+55).dp,
                        y = if (vehicle.name == "Cargo freight") (+18).dp else if (vehicle.name == "Air freight") (+5).dp else (-50).dp
                    )
                    .scale(1.8f),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 10.dp, start = 10.dp)
                    .wrapContentSize()
            ) {
                Text(
                    text = vehicle.name, style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = vehicle.type,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun VehicleItemScreenPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            VehicleItem(modifier = Modifier, vehicles[2], 150.dp)
        }
    }
}
