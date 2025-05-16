package org.mediaapp.controller;

import org.mediaapp.model.User;
import org.mediaapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){

        return userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }
}
