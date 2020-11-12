package com.learntech.services

import com.learntech.dto.UserRequest
import com.learntech.dto.UserResponse
import com.learntech.model.Users
import com.learntech.repositories.UsersRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserManagementService {
    fun getUsers(): Flux<UserResponse>
    fun getUserById(id: Int): Mono<UserResponse>
    fun createUser(userRequest: Mono<UserRequest>): Mono<UserResponse>
    fun updateUser(userRequest: Mono<UserRequest>): Mono<UserResponse>
    fun deleteUser(id: Int): Mono<UserResponse>
}

class UserManagementServiceImpl(private val usersRepository: UsersRepository) : UserManagementService {
    override fun getUsers(): Flux<UserResponse> {
        return usersRepository.findAll().map { u ->
            UserResponse(u.id, u.firstName, u.lastName, u.email, u.password, u.username, u.userEnabled, u.tokenExpired, u.createdOn, u.lastLogin)
        }
    }

    override fun getUserById(id: Int): Mono<UserResponse> {
        return usersRepository.findById(id).map { u ->
            UserResponse(u.id, u.firstName, u.lastName, u.email, u.password, u.username, u.userEnabled, u.tokenExpired, u.createdOn, u.lastLogin)
        }
    }

    override fun createUser(userRequest: Mono<UserRequest>): Mono<UserResponse> {
        return userRequest.map { u ->
            Users(null, u.firstName, u.lastName, u.email, u.password, u.username)
        }.map { usersRepository.save(it) }
            .flatMap { u -> u }
            .map { u ->
                UserResponse(u.id, u.firstName, u.lastName, u.email, u.password, u.username, u.userEnabled, u.tokenExpired, u.createdOn, u.lastLogin)
            }
    }

    override fun updateUser(userRequest: Mono<UserRequest>): Mono<UserResponse> {
        return userRequest.map { u ->
            u.id?.let { usersRepository.findById(it) }?. map {
                updatedUser(it, u)
            }?.map { usersRepository.save(it) }
                ?.flatMap { it }
        }.flatMap { it }.map {
            UserResponse(it.id, it.firstName, it.lastName, it.email, it.password, it.username, it.userEnabled, it.tokenExpired, it.createdOn, it.lastLogin)
        }
    }

    override fun deleteUser(id: Int): Mono<UserResponse> {
        TODO("Not yet implemented")
    }

    private fun updatedUser(user: Users, userRequest: UserRequest): Users {
        user.firstName = userRequest.firstName
        user.lastName = userRequest.lastName
        user.email = userRequest.email
        user.password = userRequest.password
        return user
    }
}
