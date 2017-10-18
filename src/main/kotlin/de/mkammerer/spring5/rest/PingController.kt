package de.mkammerer.spring5.rest

import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerResponse

class PingController {
    fun ping(): HandlerFunction<ServerResponse> = HandlerFunction<ServerResponse> { request ->
        ServerResponse.ok().body(BodyInserters.fromObject("Pong"))
    }
}