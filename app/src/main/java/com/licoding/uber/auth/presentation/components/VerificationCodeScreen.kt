package com.licoding.uber.auth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun VerificationCodeScreen() {
    val verificationCode = IntArray(6)

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter Verification Code",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 0 until 6) {
                TextField(
                    value = if(verificationCode[i] == 0) "" else verificationCode[i].toString(),
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() || newValue.length == 1) {
                            verificationCode.plus(newValue.toInt())
                            println(verificationCode)
                        }
                    },
                    modifier = Modifier.width(50.dp).focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {  },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Verify")
        }
    }
}

@Composable
fun VerificationCodeInputBox(value: String, onValueChange: (Int) -> Unit, modifier: Modifier) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.isNotEmpty() || newValue.length == 1) {
                onValueChange(newValue.toInt())
            }
        },
        modifier = modifier.width(50.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = MaterialTheme.typography.bodyLarge
    )
//    VerificationCodeInputBox(
//        value = if(verificationCode[i] == 0) "" else verificationCode[i].toString(),
//        onValueChange = { newValue ->
//            verificationCode.plus(newValue)
//            println(verificationCode)
//        },
//        modifier = Modifier.focusRequester(focusRequester)
//    )
}

