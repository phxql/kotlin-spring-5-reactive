package de.mkammerer.spring5.rest

import de.mkammerer.spring5.business.HelloService
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerResponse

class HelloController(
        private val helloService: HelloService
) {
    fun hello(): HandlerFunction<ServerResponse> = HandlerFunction<ServerResponse> { request ->
        val name = request.pathVariable("name")
        ServerResponse.ok().body(BodyInserters.fromPublisher(helloService.greeting(name), String::class.java))
    }
}