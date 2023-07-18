package com.stalin.deployment.Resources;

import com.stalin.deployment.Model.User;
import com.stalin.deployment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

    @Autowired
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    Flux<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    Mono<User> getUser(@PathVariable String id){
        return userService.find(id);
    }

    @PostMapping
    Mono<User> createUser(@RequestBody User user){
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteUser(@PathVariable String id){
        return userService.delete(id);
    }
}
