package de.mkammerer.spring5.business

import reactor.core.publisher.Mono

interface HelloService {
    fun greeting(): Mono<String>
}

class HelloServiceImpl : HelloService {
    override fun greeting(): Mono<String> = Mono.just("Hello world")
}