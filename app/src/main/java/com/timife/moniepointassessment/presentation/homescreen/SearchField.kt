package com.timife.moniepointassessment.presentation.homescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme
import com.timife.moniepointassessment.ui.theme.Orange

@Composable
fun SearchField(
    modifier: Modifier,
    isSearchBarExpanded: Boolean,
    onSearchBarActive: (Boolean) -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.Gray),
        placeholder = {
            Text(
                text = "Enter the receipt number ...",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
            )
        },
        shape = CircleShape,
        maxLines = 1,
        readOnly = isSearchBarExpanded,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .background(Orange)
                    .size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_scanner_svgrepo_com),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        modifier = modifier
            .border(1.dp, Color.Transparent)
            .padding(10.dp)
            .onFocusChanged { focusState ->
                Log.d("isFocused", focusState.isFocused.toString())
                onSearchBarActive(focusState.isFocused)
            },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchFieldScreenPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
        }
    }
}