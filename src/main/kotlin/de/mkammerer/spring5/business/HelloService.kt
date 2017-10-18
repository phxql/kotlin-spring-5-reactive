package de.mkammerer.spring5.business

import reactor.core.publisher.Mono

interface HelloService {
    fun greeting(name: String): Mono<String>
}

class HelloServiceImpl : HelloService {
    override fun greeting(name: String): Mono<String> = Mono.just("Hello $name")
}