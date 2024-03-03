package com.licoding.uber.auth.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.uber.R
import com.licoding.uber.auth.domain.util.detectCountry
import com.licoding.uber.auth.domain.getCountryCode
import com.licoding.uber.auth.domain.getPhoneNumberLen
import com.licoding.uber.auth.presentation.components.CustomAction
import com.licoding.uber.auth.presentation.components.CustomButtom
import com.licoding.uber.auth.presentation.components.CustomDivider
import java.util.*


@Composable
fun LoginScreen(
    context: Context,
    navController: NavController,
    state: AuthUIState,
    onEvent: (AuthUIEvent) -> Unit,
    onGoogleSignUp: () -> Unit
) {
    val country = detectCountry(context)
    val code = getCountryCode(context)
    val len = getPhoneNumberLen(country).toInt()
    var number by remember { mutableStateOf("") }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "Enter your mobile number",
                fontSize = 25.sp,
                color = Color.Black
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = country.uppercase(Locale.ROOT),
                    modifier = Modifier
                        .width(100.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(16.dp, 10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background
                )
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Row(
                    modifier = Modifier
                        .border(2.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .padding(16.dp, 10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = code,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    BasicTextField(
                        onValueChange = {
                            number = it
                            if (number.length == len && state.phoneNumberError != null) {
                                onEvent(AuthUIEvent.SetPhoneNumberError(""))
                            } else if (number.length < 11) {
                                onEvent(AuthUIEvent.OnPhoneNumberChange(number))
                            }
                        },
                        value = state.phoneNumber ?: "",
                        decorationBox = { innerTextField ->
                            if (number.isEmpty()) {
                                Text(text = "Mobile number", color = Color.Gray)
                            }
                            innerTextField()
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword
                        )
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        if (state.phoneNumberError != null && state.phoneNumberError != "") {
            Text(
                text = state.phoneNumberError,
                fontSize = 15.sp,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        CustomButtom(
            text = "Continue",
            color = Color.Black,
            textColor = Color.White,
            onClick = {
                if(state.phoneNumber?.length == len) {
                    navController.navigate("verification")
                    if (state.phoneNumberError != null) {
                        onEvent(AuthUIEvent.SetPhoneNumberError(""))
                    }
                } else {
                    onEvent(AuthUIEvent.SetPhoneNumberError("Invalid phone number."))
                }
            },
        )
        Spacer(
            modifier = Modifier.height(23.dp)
        )
        CustomDivider()

        Spacer(
            modifier = Modifier.height(7.dp)
        )
        CustomButtom(
            text = "Continue with Google",
            color = Color.LightGray,
            icon = null,
            textColor = Color.Black,
            onClick = {
                onGoogleSignUp()
            },
            painter = painterResource(R.drawable.google)
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        CustomButtom(
            text = "Continue with Apple",
            color = Color.LightGray,
            icon = null,
            textColor = Color.Black,
            onClick = {
                navController.navigate("verification")
            },
            painter = painterResource(R.drawable.apple)
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        CustomButtom(
            text = "Continue with Facebook",
            color = Color.LightGray,
            textColor = Color.Black,
            onClick = {
                navController.navigate("verification")
            },
            painter = painterResource(R.drawable.facebook)
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        CustomButtom(
            text = "Continue with Email",
            color = Color.LightGray,
            icon = Icons.Default.Email,
            textColor = Color.Black,
            onClick = {
                navController.navigate("emailsignin")
            },
        )
        Spacer(
            modifier = Modifier.height(23.dp)
        )
        CustomDivider()
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        CustomAction()
        Spacer(
            modifier = Modifier.height(23.dp)
        )
        Text(
            text = "By proceeding, you consent to get calls, WhatsApp or SMS messages, including by automated means, from Uber and its affiliates to the number provided.",
            fontSize = 11.sp,
            color = Color.Black,
            fontWeight = FontWeight.Thin
        )
    }
}