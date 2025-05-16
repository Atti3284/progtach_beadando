package org.mediaapp.service;

import org.mediaapp.model.User;
import org.mediaapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id, User userData) {
        return repository.findById(id).map(user -> {
            user.setUsername(userData.getUsername());
            user.setEmail(userData.getEmail());
            return repository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
