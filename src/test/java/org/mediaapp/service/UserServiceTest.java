package org.mediaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mediaapp.model.User;
import org.mediaapp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testGetAll() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> all = userService.getAll();
        assertEquals(2, all.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        User user = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getById(1L);
        assertNotNull(result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(userRepository.findById(100L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> userService.getById(100L));
        String expectedMessage = "User not found with id 100";
        assertTrue(exception.getMessage().contains("User not found with id"));
    }

    @Test
    void testCreate() {
        User user = mock(User.class);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(user);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdate() {
        User existing = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(existing);

        User updates = mock(User.class);
        when(updates.getUsername()).thenReturn("newUsername");
        when(updates.getPassword()).thenReturn("newPassword");
        when(updates.getEmail()).thenReturn("new@email.com");

        User result = userService.update(1L, updates);

        verify(existing, times(1)).setUsername("newUsername");
        verify(existing, times(1)).setPassword("newPassword");
        verify(existing, times(1)).setEmail("new@email.com");
        verify(userRepository, times(1)).save(existing);
        assertEquals(existing, result);
    }

    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById(1L);
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}