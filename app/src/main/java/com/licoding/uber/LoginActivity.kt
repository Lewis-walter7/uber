package com.licoding.uber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.licoding.uber.auth.domain.signup.FirebasePhoneSignUp
import com.licoding.uber.auth.domain.signup.FirebasePhoneSignUp.callbacks
import com.licoding.uber.auth.presentation.AuthViewModel
import com.licoding.uber.auth.presentation.LoginScreen
import com.licoding.uber.auth.presentation.Welcome
import com.licoding.uber.auth.presentation.components.VerificationCodeScreen
import com.licoding.uber.ui.theme.UberTheme
import java.util.concurrent.TimeUnit

class LoginActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<AuthViewModel>()

        setContent {
            val state by viewModel.state.collectAsState()
            UberTheme {
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "welcome"
                    ) {
                        composable("welcome") {
                            Welcome(navController)
                        }
                        composable("login") {
                            LoginScreen(
                                context = this@LoginActivity,
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("verification") {
                            VerificationCodeScreen(navController)
                        }
                    }
                }
            }
        }
    }

    private fun startPhoneVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(FirebasePhoneSignUp.auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}