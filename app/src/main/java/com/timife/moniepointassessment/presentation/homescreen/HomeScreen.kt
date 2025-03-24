package com.timife.moniepointassessment.presentation.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.timife.moniepointassessment.domain.vehicles
import com.timife.moniepointassessment.presentation.appbars.AppBar
import com.timife.moniepointassessment.presentation.calculate.ItemData
import com.timife.moniepointassessment.presentation.calculate.composables
import com.timife.moniepointassessment.presentation.search.SearchResultCard
import com.timife.moniepointassessment.presentation.search.SearchViewModel
import kotlinx.coroutines.delay

val homeComposable = listOf(
    ItemData {
        Text(
            text = "Tracking",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    },
    ItemData {
        TrackingItem(modifier = Modifier.fillMaxWidth())
    }
)


@Composable
fun HomeScreen(modifier: Modifier, viewModel: SearchViewModel = hiltViewModel()) {
    var isSearchBarExpanded by remember { mutableStateOf(true) }
    val state = viewModel.searchList.collectAsStateWithLifecycle()
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        isContentVisible = true
    })


    Scaffold(
        topBar = {
            AppBar(isSearchBarExpanded, onSearchBarExpanded = {
                isSearchBarExpanded = it
            }, value = searchText.value, onValueChange = {
                viewModel.onSearchTextChange(it)
            })
        }
    ) { paddingValues ->
        if (isSearchBarExpanded) {
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .verticalScroll(rememberScrollState(), true),

                    ) {
                    Spacer(modifier = Modifier.height(15.dp))

                    homeComposable.forEachIndexed { index, itemData ->
                        AnimatedVisibility(
                            visible = isContentVisible,
                            enter = slideInVertically(
                                initialOffsetY =
                                { return@slideInVertically (composables.lastIndex - index) * 100.dp.value.toInt() }
                            ,animationSpec = tween(
                                    durationMillis = 600,
                                    easing = Ease
                                )) + fadeIn(),
                        ) {
                            itemData.composable()
                        }
                    }

                    Text(
                        text = "Available Vehicles",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )

                    val screenWidth = LocalConfiguration.current.screenWidthDp

                    val itemOffset = remember { Animatable(0f) }
                    val staggeredDelay = 50
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState(), true
                        ).height((height * 0.26).dp)
                    ) {
                        vehicles.forEachIndexed { index, _ ->
                            var isVisible by remember {
                                mutableStateOf(false)
                            }

                            LaunchedEffect(key1 = index, block = {
                                delay(staggeredDelay.toLong())
                                isVisible = true
                            })
                            AnimatedVisibility(
                                visible = isVisible,
                                enter = fadeIn(
                                    initialAlpha = 0.3f
                                ) + slideInHorizontally(
                                    initialOffsetX = { screenWidth },
                                    animationSpec = tween(
                                        durationMillis = 600,
                                        easing = Ease
                                    )
                                ),
                                modifier = Modifier.offset(x = itemOffset.value.dp)
                            ) {
                                VehicleItem(
                                    modifier = Modifier,
                                    vehicles[index],
                                    height = (height/4).dp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        } else {
            Box(modifier = Modifier) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    SearchResultCard(
                        modifier = Modifier.padding(paddingValues),
                        items = state.value
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }

}