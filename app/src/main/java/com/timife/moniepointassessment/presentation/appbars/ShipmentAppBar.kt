package com.timife.moniepointassessment.presentation.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.domain.Status
import com.timife.moniepointassessment.domain.shipments
import com.timife.moniepointassessment.presentation.calculate.bounceClickable
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Orange

@Composable
fun ShipmentAppBar(
    navController: NavController,
    onTabClicked: (Status) -> Unit
) {
    val tabs = Status.entries.toList()
    var tabIndex by remember { mutableIntStateOf(0) }

    Column {
        ReusableAppBar(navController, stringResource(R.string.shipment_history))
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            edgePadding = 5.dp,
            indicator = { tabPositions ->
                if (tabIndex < tabPositions.size) {
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[tabIndex])
                            .height(4.dp)
                            .background(
                                Orange
                            )
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index, modifier = Modifier.bounceClickable(), onClick = {
                    tabIndex = index
                    onTabClicked(title)
                }, text = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title.status,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                                .background(if (tabIndex == index) Orange else Color.Gray.copy(alpha = 0.2f))
                                .height(18.dp)
                                .wrapContentWidth()
                                .padding(start = 7.dp, end = 7.dp)
                        ) {
                            Text(
                                text = if (title == Status.ALL) shipments.size.toString() else {
                                    shipments.filter {
                                        it.status == title
                                    }.size.toString()
                                },
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surfaceVariant)
                            )
                        }
                    }
                })
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentAppBarPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            ShipmentAppBar(rememberNavController(), onTabClicked = {})
        }
    }
}