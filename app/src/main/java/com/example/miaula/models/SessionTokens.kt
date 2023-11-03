package com.example.miaula.models

data class SessionTokens(
    val user: User,
    val token: String,
    val refreshToken: String
)

data class Users(
    val id: Long,
    val username: String,
)