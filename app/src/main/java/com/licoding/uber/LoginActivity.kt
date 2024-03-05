package com.licoding.uber

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.licoding.uber.auth.data.remote.signup.FirebasePhoneSignUp
import com.licoding.uber.auth.data.remote.signup.FirebasePhoneSignUp.callbacks
import com.licoding.uber.auth.data.remote.signup.google.GoogleAuthUiClient
import com.licoding.uber.auth.presentation.AuthViewModel
import com.licoding.uber.auth.presentation.LoginScreen
import com.licoding.uber.auth.presentation.Welcome
import com.licoding.uber.auth.presentation.components.authScreens.LoginPage
import com.licoding.uber.auth.presentation.components.VerificationCodeScreen
import com.licoding.uber.auth.presentation.components.authScreens.RegisterPage
import com.licoding.uber.ui.theme.UberTheme
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class LoginActivity: ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val currentUser = Firebase.auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val viewModel by viewModels<AuthViewModel>()

        setContent {
            val state by viewModel.state.collectAsState()

            UberTheme {
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if(result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if(state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Sign in successful",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.navigate("home")
                            viewModel.resetState()
                        }
                    }
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
                                onEvent = viewModel::onEvent,
                                onGoogleSignUp = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }
                        composable("verification") {
                            VerificationCodeScreen(navController)
                        }
                        composable("emailsignin") {
                            LoginPage(
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("emailsignup") {
                            RegisterPage(
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
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