package com.timife.moniepointassessment.presentation.calculate

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timife.moniepointassessment.R
import com.timife.moniepointassessment.ui.theme.MoniepointAssessmentTheme

@Composable
fun PackagingItemm(hint: String) {
    var name by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        shape = RoundedCornerShape(15.dp),
        value = name,
        onValueChange = {
            name = it
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
        modifier = Modifier
            .border(1.dp, Color.Transparent)
            .fillMaxWidth(),
        leadingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.package_icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(7.dp))
                androidx.compose.material.Divider(
                    color = Color.LightGray, modifier = Modifier
                        .height(35.dp)
                        .width(1.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                )
            }
        },
        trailingIcon = {
            Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = null)
        },
        placeholder = {
            Text(text = hint, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            backgroundColor = MaterialTheme.colorScheme.surface,
        )
    )

}

@Preview(showBackground = true)
@Composable
fun PackagingItemPreview() {
    MoniepointAssessmentTheme {
        Box(modifier = Modifier.fillMaxHeight()) {
            PackagingItemm("Box")
        }
    }
}