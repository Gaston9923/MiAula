package com.example.miaula.network

import okhttp3.Request

enum class AuthorizationType {
    ACCESS_TOKEN, PASSWORD_TOKEN, NONE, BIOMETRIC_TOKEN;
    companion object {
        fun fromRequest(request: Request): AuthorizationType {
            val tag = request.tag(AuthorizationType::class.java)
            return tag ?: ACCESS_TOKEN
        }
    }
}