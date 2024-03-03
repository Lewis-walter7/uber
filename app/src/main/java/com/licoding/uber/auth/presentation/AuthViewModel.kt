package com.licoding.uber.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.licoding.uber.core.domain.SignInResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val _state = MutableStateFlow(AuthUIState())
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
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

            AuthUIEvent.OnLoginButtonClicked -> {
                viewModelScope.launch {
                    _isLoading.value = true
                    delay(3000)
                    _isLoading.value = false
                }
            }
            is AuthUIEvent.OnLoginEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is AuthUIEvent.OnLoginPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is AuthUIEvent.OnNameChange -> TODO()
            AuthUIEvent.OnRegisterButtonClicked -> TODO()
            is AuthUIEvent.OnRegisterEmailChange -> TODO()
            is AuthUIEvent.OnRegisterPasswordChange -> TODO()
        }
    }

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { AuthUIState() }
    }
}