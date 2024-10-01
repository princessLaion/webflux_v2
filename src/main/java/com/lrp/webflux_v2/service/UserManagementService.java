package com.lrp.webflux_v2.service;

import com.lrp.webflux_v2.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserManagementService {

    Flux<User> getUsers();

    Mono<User> getUserById(Long id);

    Mono<User> createUser(User user);

    Mono<User> updateUser(Long id, User user);

    Mono<Void> deleteUserById(Long id);

    Mono<User> deleteUserByIdAndReturn(Long id);
}
