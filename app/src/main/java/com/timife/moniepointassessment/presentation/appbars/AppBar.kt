package com.timife.moniepointassessment.presentation.appbars

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.presentation.homescreen.SearchField
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun AppBar(
    isSearchBarExpanded: Boolean,
    onSearchBarExpanded: (Boolean) -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp


    val searchBarOffsetY by animateDpAsState(
        targetValue = if (isSearchBarExpanded) 0.dp else (-10).dp,
        animationSpec = tween(durationMillis = 600),
        label = ""
    )


    val searchBarWidth by animateDpAsState(
        targetValue = if (isSearchBarExpanded) screenWidth.dp else (screenWidth - 40).dp,
        animationSpec = tween(durationMillis = 600),
        label = ""
    )
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth().wrapContentSize(),
    ) {
        AnimatedVisibility(
            visible = isSearchBarExpanded,
            enter = fadeIn() + expandVertically() + slideInVertically(
                animationSpec = tween(
                    durationMillis = 600,
                )
            ),
            exit = shrinkVertically() + fadeOut() + slideOutVertically(
                animationSpec = tween(
                    durationMillis = 600,
                )
            )
        ) {
            AppBarRow()
        }


        Box {
            Row {
                AnimatedVisibility(
                    visible = !isSearchBarExpanded,
                    enter = fadeIn() + slideInHorizontally(animationSpec = tween(easing = EaseInOutCubic)),
                    exit = fadeOut() + slideOutHorizontally(animationSpec = tween(easing = EaseInOutCubic))
                ) {
                    IconButton(
                        onClick = {
                            onSearchBarExpanded(!isSearchBarExpanded)
                            focusManager.clearFocus(true)
                            onValueChange("")
                        }, modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                    SearchField(
                        modifier = Modifier
                            .offset(y = searchBarOffsetY)
                            .width(searchBarWidth)
                            .focusable(true),
                        isSearchBarExpanded,
                        onSearchBarActive = {
                            onSearchBarExpanded(!it)
                        },
                        value = value,
                        onValueChange = onValueChange
                    )
            }
        }
    }

}

@Composable
fun AppBarRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_dp),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface) // Clips the image to a circle shape
                .clickable { /* Handle click event */ }
                .size(35.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { }) {
                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = MaterialTheme.colorScheme.background
                )
                Text(
                    text = "Your location",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight(200)
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {

                }) {
                Text(
                    text = "Wertheimer, Illinois",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary)
                .size(45.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarScreenPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {

        }
    }
}