package com.timife.moniepointassessment.presentation.calculate

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timife.moniepointassessment.presentation.ScreenRoute
import com.timife.moniepointassessment.presentation.appbars.ReusableAppBar

data class ItemData(val composable: @Composable () -> Unit)


val composables = listOf(
    ItemData {
        Text(
            text = "Destination",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    },

    ItemData { DestinationCard(modifier = Modifier) },
    ItemData { Spacer(modifier = Modifier.height(5.dp)) },

    ItemData {
        Text(
            text = "Packaging",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    },
    ItemData {
        Text(
            text = "What are you sending?",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        )
    },
    ItemData { PackagingItemm(hint = "Box") },
    ItemData { Spacer(modifier = Modifier.height(5.dp)) }
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalculateScreen(navController: NavController) {
    val itemDelayDuration = 200
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        isVisible = true
    })


    Scaffold(
        topBar = {
            ReusableAppBar(navController = navController, "Calculate")
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(rememberScrollState(), enabled = true)
                    .padding(10.dp)

            ) {
                Spacer(modifier = Modifier.height(5.dp))
                composables.forEachIndexed { index, itemData ->

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically(
                            initialOffsetY =
                            { return@slideInVertically (composables.lastIndex - index) * itemDelayDuration.dp.value.toInt() }
                        ) + fadeIn(),
                    ) {
                        itemData.composable()
                    }
                }

                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = "What are you sending?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                )
                Categories(items = categories)
                Spacer(modifier = Modifier.height(5.dp))

                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    )
                ) {
                    ReusableButton(
                        "Calculate",
                        modifier = Modifier
                            .padding(bottom = 100.dp)
                            .fillMaxWidth()
                            .bounceClickable(),
                        onClick = {
                            navController.navigate(ScreenRoute.SuccessScreen.route)
                        })

                }
            }
        }
    }
}
