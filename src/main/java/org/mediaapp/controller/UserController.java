package org.mediaapp.controller;

import org.mediaapp.model.User;
import org.mediaapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/{id}")
    public User getUserById(@PathVariable long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nincs ilyen felhasználó: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
