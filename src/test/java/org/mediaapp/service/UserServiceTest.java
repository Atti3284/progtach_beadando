package org.mediaapp.service;

import org.mediaapp.model.User;
import org.mediaapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserRepository repo = mock(UserRepository.class);
    private final UserService service = new UserService(repo);

    @Test
    void testCreateUser() {
        User user = new User("john", "john@example.com");
        when(repo.save(user)).thenReturn(user);

        User saved = service.create(user);
        assertEquals("john", saved.getUsername());
        assertEquals("john@example.com", saved.getEmail());
    }

    @Test
    void testGetUserById() {
        User user = new User("anna", "anna@example.com");
        when(repo.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = service.getById(1L);
        assertTrue(result.isPresent());
        assertEquals("anna", result.get().getUsername());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(
                new User("a", "a@mail.com"),
                new User("b", "b@mail.com")
        );
        when(repo.findAll()).thenReturn(users);

        List<User> all = service.getAll();
        assertEquals(2, all.size());
    }
}
