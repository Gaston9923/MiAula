package com.example.miaula.sharedPreferences

import android.content.Context
import com.example.miaula.models.User

import com.google.gson.Gson


class SessionPreferences(
    context: Context
) : BasePreferences() {

    init {
        init(context, SESSION_NAME)
    }

    fun isLoggedIn(): Boolean {
        val token = get(SESSION_TOKEN)
        return token != null && token != ""
    }

    fun setToken(token: String) {
        set(SESSION_TOKEN, token)
    }

    fun getToken() = get(SESSION_TOKEN)


    fun setRefreshToken(refreshToken: String) {
        set(SESSION_REFRESH_TOKEN, refreshToken)
    }

    fun getEmail() = get(SESSION_EMAIL)

    fun setEmail(email: String) {
        set(SESSION_EMAIL, email)
    }

    fun getRefreshToken() = get(SESSION_REFRESH_TOKEN)

    fun setPasswordToken(passwordToken: String) {
        set(SESSION_PASSWORD_TOKEN, passwordToken)
    }

    fun getPasswordToken() = get(SESSION_PASSWORD_TOKEN)

    fun setUser(user: User) {
        set(SESSION_USER, user)
    }

    fun getUser(): User? = run {
        val userJson = get(SESSION_USER)
        if (userJson != null) {
            return Gson().fromJson(userJson, User::class.java)
        } else {
            return null
        }
    }
//
//    fun setUserDetails(userDetails: DetailsCourse) {
//        set(SESSION_USER_DETAILS, userDetails)
//    }
//
//    fun getUserDetails(): DetailsCourse? = run {
//        val userJson = get(SESSION_USER_DETAILS)
//        if (userJson != null) {
//            return Gson().fromJson(userJson, DetailsCourse::class.java)
//        } else {
//            return null
//        }
//    }



    companion object {
        const val SESSION_NAME = "session_preferences"
        const val SESSION_TOKEN = "session_token"
        const val SESSION_PASSWORD_TOKEN = "session_password_token"
        const val SESSION_REFRESH_TOKEN = "session_refresh_token"
        const val SESSION_EMAIL = "session_email"
        const val SESSION_USER = "session_user"
        const val SESSION_USER_DETAILS = "session_user_details"
    }
}