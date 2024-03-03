package com.licoding.uber.core.domain

data class User(
    val name: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val createdAt: Long? = 0,
    val profileImage: String? = null,
    val userId: String
)

data class SignInResult(
    val data: User?,
    val errorMessage: String?
)
