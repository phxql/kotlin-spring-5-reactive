package de.mkammerer.spring5

import de.mkammerer.spring5.business.HelloServiceImpl
import de.mkammerer.spring5.rest.HelloController
import de.mkammerer.spring5.rest.PingController
import org.springframework.beans.factory.getBean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import reactor.ipc.netty.http.server.HttpServer

private const val PORT = 8080

fun main(args: Array<String>) {
    // Bootstrap Spring context
    val context = GenericApplicationContext()
    context.registerBean { HelloController(context.getBean()) }
    context.registerBean { PingController() }
    context.registerBean { HelloServiceImpl() }
    context.refresh()

    // Register HTTP routes
    val route = RouterFunctions
            .route(RequestPredicates.GET("/hello/{name}"), context.getBean<HelloController>().hello())
            .andRoute(RequestPredicates.GET("/ping"), context.getBean<PingController>().ping())

    // Create server
    val httpHandler = RouterFunctions.toHttpHandler(route)
    val adapter = ReactorHttpHandlerAdapter(httpHandler)
    val server = HttpServer.create(PORT)

    // Start server and block
    server.startAndAwait(adapter)
}

