package com.licoding.uber.auth.domain.models

data class AuthRequest(
    val email: String,
    val password: String,
    val name: String? = null
)
