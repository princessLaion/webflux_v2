package com.lrp.webflux_v2.repository;

import com.lrp.webflux_v2.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    Mono<User> findById(Long id);
    Mono<Void> deleteUserById(Long id);
}
