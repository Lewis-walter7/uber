package com.licoding.uber.auth.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeScreen(navController: NavController) {
    var otp by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Verification"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 70.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Code has been sent to",
                    fontSize = 18.sp,
                )
                Text(
                    text = "+254759170624",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(23.dp))

            BasicTextField(
                onValueChange = {
                    if (otp.length < 7) {
                        otp = it
                    }
                },
                value = otp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(6) { index ->
                            val char = when {
                                index >= otp.length -> ""
                                else -> otp[index].toString()
                            }

                            val isFocussed = otp.length == index
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = char,
                                modifier = Modifier
                                    .width(40.dp)
                                    .border(
                                        if (isFocussed) 2.dp else 1.dp,
                                        if (isFocussed) Color.LightGray else Color.DarkGray,
                                        RoundedCornerShape(4.dp)
                                    )
                                    .padding(6.dp),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(23.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Haven't received the verification code?"
                )
                Spacer(modifier = Modifier.height(10.dp))
                ClickableText(
                    text = AnnotatedString("Resend"),
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black, RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Verify OTP",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


