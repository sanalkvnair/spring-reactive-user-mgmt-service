package com.learntech.dto

import java.time.LocalDateTime

data class UserRequest(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val username: String,
    val userEnabled: Boolean = true,
    val tokenExpired: Boolean = true,
    val createdOn: LocalDateTime? = null,
    val lastLogin: LocalDateTime? = null
)

data class UserResponse(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val username: String,
    val userEnabled: Boolean = true,
    val tokenExpired: Boolean = true,
    val createdOn: LocalDateTime? = null,
    val lastLogin: LocalDateTime? = null
)
