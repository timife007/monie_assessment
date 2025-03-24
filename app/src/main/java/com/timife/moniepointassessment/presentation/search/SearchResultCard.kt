package com.timife.moniepointassessment.presentation.search

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.domain.Item
import com.timife.moniepointassessment.domain.searchItems
import com.timife.moniepointassessment.ui.theme.LighterGrey
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultCard(modifier: Modifier, items: List<Item>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        LazyColumn {
            items(items.size) { index ->

                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp
                val animatedAlpha = remember {
                    Animatable(0f)
                }
                val animatedTranslationY = remember {
                    Animatable(screenHeight.toFloat())
                }
                LaunchedEffect(key1 = null, block = {
                    animatedAlpha.animateTo(
                        1f,
                        tween(0, easing = Ease)
                    )
                    animatedTranslationY.animateTo(
                        0f,
                        tween(700, easing = Ease)
                    )
                })

                Spacer(modifier = Modifier.height(15.dp))
                SearchItem(
                    item = items[index],
                    modifier = Modifier
                        .alpha(animatedAlpha.value)
                        .animateItemPlacement()
                        .offset(y = animatedTranslationY.value.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                if (index < items.size) {
                    HorizontalDivider(modifier = Modifier, color = LighterGrey)
                }
            }
        }

    }
}

@Composable
fun SearchItem(item: Item,modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .height(IntrinsicSize.Min)
    ) {
        Image(
            painter = painterResource(id = R.drawable.delivery_box_11395_4),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary) // Clips the image to a circle shape
                .clickable { /* Handle click event */ }
                .size(35.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.number,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(5.dp)
                )
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = item.from,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        tint = Color.Gray,
                        modifier = Modifier.size(15.dp),
                        contentDescription = null
                    )
                    Text(
                        text = item.to,
                        style = MaterialTheme.typography.bodySmall.copy(Color.Gray)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultCardPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            SearchResultCard(modifier = Modifier, searchItems)
        }
    }
}

