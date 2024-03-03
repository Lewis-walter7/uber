package com.licoding.uber.auth.presentation

sealed interface AuthUIEvent {
    data object OnVerifyButtonClicked: AuthUIEvent
    data class OnPhoneNumberChange(val phoneNumber: String) : AuthUIEvent
    data class SetPhoneNumberError(val error: String) : AuthUIEvent
    data class OnLoginPasswordChange(val password: String) : AuthUIEvent
    data class OnLoginEmailChange(val email: String) : AuthUIEvent
    data class OnNameChange(val name: String) : AuthUIEvent
    data object OnLoginButtonClicked : AuthUIEvent
    data object OnRegisterButtonClicked : AuthUIEvent
    data class OnRegisterEmailChange(val email: String) : AuthUIEvent
    data class OnRegisterPasswordChange(val email: String) : AuthUIEvent
}