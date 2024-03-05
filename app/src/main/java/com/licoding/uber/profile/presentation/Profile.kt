package com.licoding.uber.profile.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.licoding.uber.core.domain.models.User

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController, user: User, onClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = user.name!!,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(5.dp))
                                .padding(3.dp, 1.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                tint = MaterialTheme.colorScheme.onBackground,
                                contentDescription = "star",
                                modifier = Modifier
                                    .size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "5.0",
                                fontSize = 14.sp
                            )
                        }
                    }
                },
                actions = {
                    AsyncImage(
                        model = user.profileImage ?: Icons.Default.Person,
                        contentDescription = "profileImage",
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .size(60.dp)
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp, 70.dp, 10.dp, 0.dp)
        ) {

            Button(
                onClick = {
                    onClick()
                }
            ) {
                Text(
                    text = "Sign Out"
                )
            }

        }
    }
}