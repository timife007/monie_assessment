package com.timife.moniepointassessment.presentation.shipment

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timife.moniepointassessment.domain.Shipment
import com.timife.moniepointassessment.presentation.appbars.ShipmentAppBar
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShipmentScreen(navController: NavController) {
    val viewModel = ShipmentViewModel()
    val state: State<List<Shipment>> = viewModel.searchList.collectAsState()

    Scaffold(
        topBar = {
            ShipmentAppBar(navController = navController, onTabClicked = {
                viewModel.onSearchTextChange(it)
            })
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                }
                item {
                    Text(
                        text = "Shipments",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                items(state.value) { item ->
                    val configuration = LocalConfiguration.current
                    val screenHeight = configuration.screenHeightDp
                    val animatedAlpha = remember {
                        Animatable(0f)
                    }
                    val animatedTranslationY = remember {
                        Animatable(screenHeight.toFloat())
                    }
                    LaunchedEffect(key1 = Unit, block = {
                        animatedAlpha.animateTo(
                            1f,
                            tween(0, easing = Ease)
                        )
                        animatedTranslationY.animateTo(
                            0f,
                            tween(700, easing = Ease)
                        )
                    })
                    ShipmentItem(
                        modifier = Modifier
                            .alpha(animatedAlpha.value)
                            .animateItemPlacement()
                            .offset(y = animatedTranslationY.value.dp),
                        shipment = item
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShipmentScreenPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            ShipmentScreen(rememberNavController())
        }
    }
}