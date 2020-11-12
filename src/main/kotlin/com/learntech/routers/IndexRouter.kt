package com.learntech.routers

import com.learntech.handlers.IndexHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class IndexRouter {
    @Bean
    public fun indexRoute(indexHandler: IndexHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.GET("/"), HandlerFunction { request: ServerRequest -> indexHandler.index(request) })
    }
}
