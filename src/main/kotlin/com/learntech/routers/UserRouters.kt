package com.learntech.routers

import com.learntech.handlers.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Configuration
class UserRouters {

    @Bean
    public fun userRoutes(userHandler: UserHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(GET("/user"), HandlerFunction { userHandler.getUsers(it) })
            .andRoute(GET("/user/{id}"), HandlerFunction { userHandler.getUserById(it) })
            .andRoute(POST("/user").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), HandlerFunction { userHandler.createUser(Mono.just(it)) })
    }
}
