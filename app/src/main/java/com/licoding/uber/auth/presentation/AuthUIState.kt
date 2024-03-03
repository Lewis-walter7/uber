package com.licoding.uber.auth.presentation

data class AuthUIState(
    val phoneNumberError: String? = null,
    val phoneNumber: String? = null,
    val password: String? = null,
    val email: String? = null,
    val signInError: String? = null,
    val isSignInSuccessful: Boolean = false,
)