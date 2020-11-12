package com.learntech.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("users")
data class Users(
    @Id val id: Int? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    val username: String,
    val userEnabled: Boolean = true,
    val tokenExpired: Boolean = true,
    val createdOn: LocalDateTime = LocalDateTime.now(),
    val lastLogin: LocalDateTime = LocalDateTime.now()
)
