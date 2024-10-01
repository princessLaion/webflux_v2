package com.lrp.webflux_v2.router;

import com.lrp.webflux_v2.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return route()
                .GET("/api/v1/users", userHandler::getUsers)
                .GET("/api/v1/users/{id}", userHandler::getUserById)
                .POST("/api/v1/users", userHandler::createUser)
                .PUT("/api/v1/users/{id}", userHandler::updateUser)
                .DELETE("/api/v1/users/{id}", userHandler::deleteUserById)
                .DELETE("/api/v1/users/{id}/return", userHandler::deleteUserByIdAndReturn)
                .build();
    }
}
