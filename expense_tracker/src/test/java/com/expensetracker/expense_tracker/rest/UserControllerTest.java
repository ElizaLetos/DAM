package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.User;
import com.expensetracker.expense_tracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers() throws Exception {

        User user1 = new User("Leslie", "leslie@gmail.com", "test123");
        User user2 = new User("Anna", "anna@gmail.com", "test123");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Leslie"))
                .andExpect(jsonPath("$[0].email").value("leslie@gmail.com"))
                .andExpect(jsonPath("$[1].name").value("Anna"))
                .andExpect(jsonPath("$[1].email").value("anna@gmail.com"));
    }

    @Test
    void getUserById() throws Exception {
        User user = new User("Leslie", "leslie@gmail.com", "test123");

        when(userService.findUserById(0)).thenReturn(user);

        mockMvc.perform(get("/api/user/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("Leslie"))
                .andExpect(jsonPath("$.email").value("leslie@gmail.com"));
    }

    @Test
    void getUserById_NotFound() {
        when(userService.findUserById(9999)).thenThrow(new RuntimeException());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User("Leslie Updated", "leslie@gmail.com", "test123");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Leslie Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("Leslie Updated"));
    }

    @Test
    void addUser() throws Exception {
        User user = new User("Mary", "mary@gmail.com", "test123");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Mary\", \"email\":  \"mary@gmail.com\", \"password\":  \"test123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("Mary"))
                .andExpect(jsonPath("$.email").value("mary@gmail.com"));
    }

    @Test
    void deleteUser() throws Exception {

        User user = new User("Leslie", "leslie@gmail.com", "test123");

        when(userService.findUserById(0)).thenReturn(user);
        doNothing().when(userService).deleteUser(0);

        mockMvc.perform(delete("/api/user/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}