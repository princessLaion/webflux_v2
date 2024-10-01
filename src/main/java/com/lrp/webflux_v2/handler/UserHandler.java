package com.lrp.webflux_v2.handler;

import com.lrp.webflux_v2.model.User;
import com.lrp.webflux_v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ServerResponse.ok()
                .body(userService.getUsers(), User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return userService.getUserById(id)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .bodyValue("User Not Found")
                );
    }

     public Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(userService::createUser)
                .flatMap(savedUser -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(savedUser));
    }


    public Mono<ServerResponse> updateUser(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(User.class)
                .flatMap(user -> userService.updateUser(id, user))
                .flatMap(updatedUser -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(updatedUser))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("User Not Found"));
    }

    // Delete user and return only status
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return userService.deleteUserById(id)
                .then(ServerResponse.ok().body(fromValue("User deleted successfully")))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .bodyValue("User Not Found")
                );
    }

    // Delete user and return deleted user details
    public Mono<ServerResponse> deleteUserByIdAndReturn(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return userService.deleteUserByIdAndReturn(id)
                .flatMap(user -> ServerResponse.ok().body(fromValue(user)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("User Not Found"));
    }




}
