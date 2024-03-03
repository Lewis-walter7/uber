package com.licoding.uber.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButtom(
    icon: ImageVector? = null,
    text: String,
    color: Color,
    textColor: Color,
    onClick: () -> Unit,
    painter: Painter? = null
) {
    TextButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color, shape = RoundedCornerShape(10.dp)),
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Spacer(
                modifier = Modifier.width(10.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor
            )
        }
    }
}