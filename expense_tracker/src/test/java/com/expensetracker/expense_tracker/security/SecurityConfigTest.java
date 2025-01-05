package com.expensetracker.expense_tracker.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void passwordEncoder() {
        assertNotNull(passwordEncoder, "BCryptPasswordEncoder bean should be configured.");
    }

    @Test
    void authenticationProvider() {
        assertNotNull(authenticationProvider, "DaoAuthenticationProvider bean should be configured.");
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void userRoleAccess() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/category/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminRoleAccess() throws Exception {
        mockMvc.perform(delete("/api/user/2"))
                .andExpect(status().isOk());
    }

}