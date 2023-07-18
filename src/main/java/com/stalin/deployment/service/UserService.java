package com.stalin.deployment.service;

import com.stalin.deployment.Model.User;
import com.stalin.deployment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> create(User user){
        return userRepository.insert(user);
    }
    public Mono<User> find(String userId){
        return userRepository.findById(userId)
                .doOnSubscribe(s -> LOGGER.debug("Search account for id=[{}]", userId));
    }
    public Flux<User> findAll(){
        return userRepository.findAll();
    }
    public Mono<Void> delete(String userId){
        return userRepository.deleteById(userId);
    }
}
