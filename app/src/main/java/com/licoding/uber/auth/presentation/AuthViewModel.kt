package com.licoding.uber.auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel: ViewModel() {
    private val _state = MutableStateFlow(AuthUIState())

    val state = _state.asStateFlow()

    fun onEvent(event: AuthUIEvent) {
        when(event) {
            AuthUIEvent.OnVerifyButtonClicked -> {

            }

            is AuthUIEvent.OnPhoneNumberChange -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            is AuthUIEvent.SetPhoneNumberError -> {
                _state.update {
                    it.copy(
                        phoneNumberError = event.error
                    )
                }
            }
        }
    }
}