package com.lrp.webflux_v2.service;

import com.lrp.webflux_v2.model.User;
import com.lrp.webflux_v2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements UserManagementService {

    private final UserRepository userRepository;

    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    if(StringUtils.isNotBlank(updatedUser.getName())) {
                        existingUser.setName(updatedUser.getName());
                    }
                    if(StringUtils.isNotBlank(updatedUser.getEmail())) {
                        existingUser.setEmail(updatedUser.getEmail());
                    }

//                    updatedUser.getName().ifPresent(email -> existingUser.setName(updatedUser.getName()));
//                    updatedUser.getEmail().ifPresent(email -> existingUser.setEmail(updatedUser.getEmail()));

                    return userRepository.save(existingUser);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public Mono<User> deleteUserByIdAndReturn(Long id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.deleteUserById(user.getId()).thenReturn(user));
    }
}

