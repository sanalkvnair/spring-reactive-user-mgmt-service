package hello

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class GreetingRouter {
    @Bean
    fun route(greetingHandler: GreetingHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), HandlerFunction { request: ServerRequest? -> greetingHandler.hello(request) })
    }
}
