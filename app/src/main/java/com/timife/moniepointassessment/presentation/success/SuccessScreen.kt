package com.timife.moniepointassessment.presentation.success

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.presentation.ScreenRoute
import com.timife.moniepointassessment.presentation.calculate.ReusableButton
import com.timife.moniepointassessment.presentation.calculate.bounceClickable
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Orange
import com.timife.moniepointassessment.ui.theme.TagGreen
import kotlinx.coroutines.delay


@Composable
fun SuccessScreen(modifier: Modifier, navController: NavController) {


    var currentCount by remember { mutableIntStateOf(1000) }
    val targetCount = 1460
    var isVisible by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = Unit, block = {
        isVisible = true
        while (currentCount < targetCount) {
            delay(10)
            currentCount += 2
        }
    })

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MoveMateLogo()
            Spacer(modifier = Modifier.height(50.dp))

            AnimatedVisibility(
                visible = isVisible,
                enter = scaleIn(
                    initialScale = 0.3f,
                    animationSpec = tween(durationMillis = 600)  // Animation duration
                ),
                exit = scaleOut(
                    targetScale = 1f,  // End at 50% of the size
                    animationSpec = tween(durationMillis = 600)  // Animation duration
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.package_icon),
                    modifier = Modifier.size(150.dp),
                    contentDescription = null
                )
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 500)
                )
            ) {

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Total Estimated Amount",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("$$currentCount")
                            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.titleSmall.fontSize)) {
                                append("USD")
                            }
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = TagGreen,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.8f),
                        text = "This amount is estimated, this will vary \nif you change your location or weight",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    ReusableButton(
                        text = "Back to home",
                        modifier = Modifier.fillMaxWidth().bounceClickable(),
                        onClick = {
                            navController.navigate(ScreenRoute.HomeScreen.route) {
                                popUpTo(ScreenRoute.HomeScreen.route)
                                launchSingleTop = false
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun MoveMateLogo() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "MoveMate",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                fontStyle = FontStyle.Italic
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.delivery_truck_11300),
            tint = Orange,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentItemPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            SuccessScreen(modifier = Modifier.align(Alignment.Center), rememberNavController())
//            MoveMateLogo()
        }
    }
}