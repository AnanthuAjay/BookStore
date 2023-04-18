package com.example.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByEmail("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("John Doe");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);
        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        userService.deleteById(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("John Doe");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setName("John Doe");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(id);
        assertNotNull(foundUser);
        assertEquals(id, foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setName("John Doe");

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setName("Jane Doe");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);

        User savedUser = userService.updateUser(id, updatedUser);
        assertNotNull(savedUser);
        assertEquals(id, savedUser.getId());
        assertEquals(updatedUser.getName(), savedUser.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateUserNotFound() {
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setName("John Doe");

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setName("Jane Doe");

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        userService.updateUser(id, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;

        userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    
    
}
