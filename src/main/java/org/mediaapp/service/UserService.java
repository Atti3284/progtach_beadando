package org.mediaapp.service;

import org.mediaapp.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private  final AtomicLong idGenerator = new AtomicLong();

    public List<User> getAll(){
        return users;
    }

    public Optional<User> getById(Long id){
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public User create(User user){
        user.setId(idGenerator.incrementAndGet());
        users.add(user);
        return user;
    }

    public boolean delete(Long id){
        return users.removeIf(u -> u.getId().equels(id));
    }
}
