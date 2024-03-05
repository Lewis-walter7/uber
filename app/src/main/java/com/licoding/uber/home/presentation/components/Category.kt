package com.licoding.uber.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.uber.home.domain.models.Category

@Composable
fun Category(
    category: Category,
    onClick: () -> Unit,
    selectedIndex: Int,
    index: Int
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        onClick()
                    }
                )
        ) {
            Image(
                painter = category.image,
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = category.label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        if(selectedIndex == index) {
            HorizontalDivider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .width(screenWidth / 2)
            )
        } else {
            HorizontalDivider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .width(screenWidth / 2)
            )
        }
    }
}