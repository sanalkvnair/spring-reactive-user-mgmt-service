package com.learntech.handlers

import com.learntech.dto.UserRequest
import com.learntech.services.UserManagementService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(val userManagementService: UserManagementService) {
    companion object {
        fun getId(serverRequest: ServerRequest): Int {
            return Integer.parseInt(serverRequest.pathVariable("id"))
        }
    }
    fun getUsers(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(userManagementService.getUsers())
    }

    fun getUserById(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(userManagementService.getUserById(getId(serverRequest)))
    }

    fun createUser(serverRequest: Mono<ServerRequest>): Mono<ServerResponse> {
        return serverRequest.map {
            it.bodyToMono(UserRequest::class.java)
                .flatMap { t -> userManagementService.createUser(Mono.just(t)) }
                .flatMap { res ->
                    ServerResponse.created(URI.create("/user/${res.id}")).contentType(MediaType.APPLICATION_JSON).body(Mono.just(res))
                }
        }.flatMap { r -> r }
    }
}
