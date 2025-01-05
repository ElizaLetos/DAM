package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.UserDAO;
import com.expensetracker.expense_tracker.entity.Role;
import com.expensetracker.expense_tracker.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<User> users = List.of(new User("Leslie", "leslie@gmail.com", "test123"));
        when(userDAO.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(users, result, "getAllUsers should return all users");
    }

    @Test
    void findUserById() {
        User users = new User("Leslie", "leslie@gmail.com", "test123");
        when(userDAO.findById(0)).thenReturn(Optional.of(users));

        User result = userService.findUserById(0);
        assertEquals(users, result, "findUserById should return the correct category when found");
    }

    @Test
    void saveUser() {
        // Mock User
        User user = new User("Leslie", "leslie@gmail.com", "test123");

        // Mock Behavior
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userDAO.save(user)).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1); // Simulate setting the ID during save
            return savedUser;
        });

        // Perform saveUser
        User result = userService.saveUser(user);

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals("encodedPassword", result.getPassword(), "Password should be encoded");
        assertEquals(1, result.getId(), "ID should be set by the DAO");
        assertEquals(user.getEmail(), result.getEmail(), "Email should match the input");
        assertEquals(user.getName(), result.getName(), "Name should match the input");
    }

    @Test
    void deleteUser() {
        when(userDAO.findById(0)).thenReturn(Optional.of(new User()));

        userService.deleteUser(0);

        verify(userDAO, times(1)).deleteById(0);
    }

    @Test
    void loadUserByUsername() {
        Role role = Role.ROLE_USER;
        User user = new User("Leslie", "leslie@gmail.com", "test123", List.of(role), null);

        when(userDAO.findByUsername("Leslie")).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("Leslie");

        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals("Leslie", userDetails.getUsername(), "Username should match");
        assertEquals(user.getPassword(), userDetails.getPassword(), "Password should match");

        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")),
                "Authorities should include ROLE_USER");
    }

    @Test
    public void loadUserByUsername_UserNotFound() {
        String username = "nonexistentUser";

        when(userDAO.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });

        assertEquals("Invalid username or password", exception.getMessage(), "Exception message should match");
    }
}