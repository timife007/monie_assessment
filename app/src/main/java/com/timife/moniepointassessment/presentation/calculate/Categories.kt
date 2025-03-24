package com.timife.moniepointassessment.presentation.calculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.ui.theme.DeepBlue
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Orange
import kotlinx.coroutines.delay

val categories = listOf(
    "Documents",
    "Glass",
    "Liquid",
    "Food",
    "Electronic",
    "Product",
    "Others"
)

@Composable
fun Categories(items: List<String>) {
    var selected by remember {
        mutableStateOf("")
    }
    val staggeredDelay = 100
    Row(verticalAlignment = Alignment.CenterVertically) {
        ChipsRow(items = items.subList(0, 4), staggeredDelay, selected, onClick = {
            selected = it
        })
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        ChipsRow(
            items = categories.subList(4, categories.size),
            staggeredDelay,
            selected,
            onClick = {
                selected = it
            })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsRow(
    items: List<String>,
    staggeredDelay: Int,
    selected: String,
    onClick: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val itemOffset = remember { Animatable(0f) }

    FlowRow(
        modifier = Modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items.forEachIndexed { index, item ->
            var isVisible by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = index, block = {
                delay(index * staggeredDelay.toLong())
                isVisible = true
            })

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    initialAlpha = 0.3f
                ) + slideInHorizontally(
                    initialOffsetX = { screenWidth },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = Ease
                    )
                ),
                modifier = Modifier.offset(x = itemOffset.value.dp)
            ) {
                ChipItem(text = item,
                    Modifier.bounceClickable(),
                    isSelected = item == selected,
                    onClick = {
                        onClick(item)
                    }
                )
            }
        }
    }
}

enum class ButtonState { Pressed, Idle }

fun Modifier.bounceClickable(
    minScale: Float = 0.7f,
) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        targetValue = if (buttonState == ButtonState.Pressed) minScale else 1f,
        label = ""
    ) {
    }
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {}
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed

                }
            }
        }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipItem(text: String, modifier: Modifier, isSelected: Boolean, onClick: () -> Unit) {
    Chip(
        modifier = modifier.padding(end = 4.dp),
        onClick = onClick,
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = null
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = if (isSelected) DeepBlue else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall.copy(color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurface)
        )
    }
}


@Composable
fun ReusableButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    ElevatedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Orange),
        modifier = modifier
    ) {
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ReusableButton(text = "Calculate", modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), onClick = {})
        }
    }
}