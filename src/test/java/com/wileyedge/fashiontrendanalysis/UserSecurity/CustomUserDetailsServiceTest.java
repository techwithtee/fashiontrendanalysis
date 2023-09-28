package com.wileyedge.fashiontrendanalysis.UserSecurity;

import com.wileyedge.fashiontrendanalysis.dao.UserDao;
import com.wileyedge.fashiontrendanalysis.model.User;
import com.wileyedge.fashiontrendanalysis.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the CustomUserDetailsService.
 * It tests various functionalities related to user authentication and authorization.
 */
@ExtendWith(SpringExtension.class)

@SpringBootTest
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService service;

    @Mock
    private UserDao userDao;

    /**
     * Test to ensure that a valid user can be loaded by their username.
     */
    @Test
    public void testLoadUserByUsername_ValidUser() {
        User mockUser = new User(1L, "testUsername", "test@example.com", "hashedPassword", "Test Designer", "123 Test St", "123-456-7890");
        mockUser.setUsername("testUser");
        mockUser.setPasswordHash("testPassHash");
        mockUser.setRole("USER");

        when(userDao.findByUsername("testUser")).thenReturn(mockUser);

        UserDetails userDetails = service.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassHash", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    /**
     * Test to handle scenarios when an invalid username is provided.
     */

    @Test
    void testLoadUserByUsername_InvalidUser() {
        when(userDao.findByUsername("invalidUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("invalidUser");
        });
    }

    /**
     * Test to handle scenarios when a null username is provided.
     */
    @Test
    void testLoadUserByUsername_NullUsername() {
        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(null);
        });
    }

    /**
     * Test to handle scenarios where there's an exception while fetching the user from the database.
     */
    @Test
    void testLoadUserByUsername_DatabaseException() {
        when(userDao.findByUsername(anyString())).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            service.loadUserByUsername("testUser");
        });
    }

    /**
     * Test to ensure that a user without a role can be loaded, and no authorities are assigned to them.
     */
    @Test
    void testLoadUserByUsername_UserWithNoRole() {
        User mockUser = new User(1L, "testUsername", "test@example.com", "hashedPassword", "Test Designer", "123 Test St", "123-456-7890");
        mockUser.setUsername("testUserWithoutRole");
        mockUser.setPasswordHash("testPassHash");
        mockUser.setRole(null); // No role set

        when(userDao.findByUsername("testUserWithoutRole")).thenReturn(mockUser);

        UserDetails userDetails = service.loadUserByUsername("testUserWithoutRole");

        assertEquals("testUserWithoutRole", userDetails.getUsername());
        assertEquals("testPassHash", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty()); // Ensure no authorities are set
    }

}
