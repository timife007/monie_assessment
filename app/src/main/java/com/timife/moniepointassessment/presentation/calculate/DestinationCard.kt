package com.timife.moniepointassessment.presentation.calculate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme

@Composable
fun DestinationCard(
    modifier: Modifier
) {
    var senderLocation by remember {
        mutableStateOf("")
    }

    var receiverLocation by remember {
        mutableStateOf("")
    }

    var appWeight by remember {
        mutableStateOf("")
    }

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.Start
        ) {
            DestinationItem(
                hint = "Sender location",
                icon =  Icons.Outlined.Unarchive,
                content = senderLocation,
                onContentChange = {
                    senderLocation = it
                }
            )
            DestinationItem(
                hint = "Receiver location",
                icon = Icons.Outlined.Archive,
                content = receiverLocation,
                onContentChange = {
                    receiverLocation = it
                }
            )
            DestinationItem(
                hint = "Approx weight",
                icon = Icons.Outlined.Scale,
                content = appWeight,
                onContentChange = {
                    appWeight = it
                }
            )
        }
    }

}


@Composable
fun DestinationItem(
    hint: String,
    icon: ImageVector,
    content: String,
    onContentChange:(String) -> Unit
) {
    OutlinedTextField(
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
        singleLine = true,
        value = content,
        onValueChange = onContentChange,
        modifier = Modifier
            .border(1.dp, Color.Transparent)
            .fillMaxWidth(),
        leadingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon, contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Divider(
                    color = Color.LightGray, modifier = Modifier
                        .height(35.dp)
                        .width(1.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                )
            }
        },
        placeholder = {
            Text(text = hint, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            backgroundColor = MaterialTheme.colorScheme.background,
        )
    )
}


@Preview(showBackground = true)
@Composable
fun DestinationCardPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            DestinationCard(modifier = Modifier)
        }
    }
}