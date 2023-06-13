package com.example.bonappetitandroid.dto

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.serialization.Serializable

@Serializable
data class Profile(val id: Int?,
                   val FIO: String,
                   val telephoneNumber: String,
                   val email: String,
                   val password: String,
                   val role: String,
                   val address: String?)

@Serializable
data class ProfileRegistration(
    val FIO: String,
    val telephoneNumber: String,
    val email: String,
    val password: String,
    val role: String,
    val address: String?
)

@Serializable
data class ProfileRegistrationWithoutRoleAndAddress(
    val FIO: String,
    val telephoneNumber: String,
    val email: String,
    val password: String
)

