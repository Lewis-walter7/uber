package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.home.domain.models.SaveModel

@Composable
fun Card(model: SaveModel?) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier
            .width(screenWidth / 1.4f)
    ) {
        if (model != null) {
            Image(
                painter = model.image,
                contentDescription = "null",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(screenWidth /1.4f)
                    .height(150.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        if (model != null) {
            Text(
                text = model.label,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        if (model != null) {
            Text(
                text = model.description,
                fontWeight = FontWeight.Thin
            )
        }
    }
}