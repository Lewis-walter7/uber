package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomInputField() {
    var whereTo by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(30.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search"
        )
        BasicTextField(
            decorationBox = {innerTextField ->
                if(whereTo.isEmpty()) {
                    Text(
                        text = "Where to?",
                        fontSize = 20.sp
                    )
                }
                innerTextField()
            },
            value = whereTo,
            onValueChange = {
                whereTo = it
            },
            modifier = Modifier
                .padding(10.dp),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        VerticalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.background
        )

        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(30.dp))
                .padding(6.dp)
        ){
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "time"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Now",
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = "time"
            )
        }
    }
}