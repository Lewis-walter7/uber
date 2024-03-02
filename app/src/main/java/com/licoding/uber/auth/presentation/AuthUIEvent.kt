package com.licoding.uber.auth.presentation

sealed interface AuthUIEvent {
    data object OnVerifyButtonClicked: AuthUIEvent
    data class OnPhoneNumberChange(val phoneNumber: String) : AuthUIEvent
    data class SetPhoneNumberError(val error: String) : AuthUIEvent
}