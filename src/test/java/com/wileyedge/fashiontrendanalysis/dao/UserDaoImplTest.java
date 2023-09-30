package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * This class provides unit tests for the UserDaoImpl class.
 * It utilizes Mockito for mocking dependencies and JUnit5 for testing.
 */
@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

    // Mocking JdbcTemplate, which is a core part of the Spring JDBC module for database operations.
    @Mock
    private JdbcTemplate jdbcTemplate;

    // Injecting the mock object into the UserDaoImpl class which we are testing.
    @InjectMocks
    private UserDaoImpl userDao;

    /**
     * Test case for creating a new user.
     * It checks if the user is created and the correct ID is returned.
     */
    @Test
    public void testCreateUser() {
        // Test data setup.
        User user = new User(null, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPasswordHash()), eq(user.getDesignerName()), eq(user.getAddress()), eq(user.getPhone()), eq(user.getRole()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);

        // Actual method call and assertion.
        Long resultId = userDao.createUser(user);
        assertEquals(1L, resultId);
    }

    /**
     * Test case for finding a user by their username.
     * It checks if the correct user details are returned.
     */
    @Test
    public void testFindByUsername() {
        // Test data setup.
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(user);

        // Actual method call and assertion.
        User result = userDao.findByUsername("Alice");
        assertEquals(user, result);
    }

    /**
     * Test case for updating user details.
     * It checks if the user details are updated correctly.
     */
    @Test
    public void testUpdateUser() {
        // Test data setup.
        User user = new User(1L, "AliceUpdated", "aliceupdated@example.com", "hashed_password_updated", "DesignerAliceUpdated", "1234 Street", "12345678901");
        user.setRole("ADMIN");

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPasswordHash()), eq(user.getDesignerName()), eq(user.getAddress()), eq(user.getPhone()), eq(user.getRole()), eq(user.getUserId()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(user);

        // Actual method call and assertion.
        User updatedUser = userDao.updateUser(user);
        assertEquals(user, updatedUser);
    }

    /**
     * Test case for deleting a user based on their ID.
     * It checks if the user is deleted successfully.
     */
    @Test
    public void testDeleteUser() {
        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);

        // Actual method call and assertion.
        boolean result = userDao.deleteUser(1L);
        assertEquals(true, result);
    }

    /**
     * Test case to handle scenarios where the requested user doesn't exist based on their username.
     * It checks if the correct exception is thrown.
     */
    @Test
    public void testFindByNonExistentUsername() {
        // Mocking the behavior of jdbcTemplate to simulate an exception scenario.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenThrow(new EmptyResultDataAccessException(1));

        // Actual method call and assertion to check if the expected exception is thrown.
        assertThrows(Exception.class, () -> {
            userDao.findByUsername("NonExistentUsername");
        });
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their ID.
     * It checks if the correct boolean value is returned when attempting to delete a non-existent user.
     */
    @Test
    public void testDeleteNonExistentUser() {
        // Mocking the behavior of jdbcTemplate to simulate a scenario where no rows are affected (i.e., user does not exist).
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(0);

        // Actual method call and assertion.
        boolean result = userDao.deleteUser(999L);
        assertEquals(false, result);
    }

    /**
     * Test to ensure a user can be fetched based on their email.
     * It checks if the correct user details are returned when querying by email.
     */
    @Test
    public void testFindByEmail() {
        // Test data setup.
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(user);

        // Actual method call and assertion.
        User result = userDao.findByEmail("alice@example.com");
        assertEquals(user, result);
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their email.
     * It checks if the correct exception is thrown when querying with a non-existent email.
     */
    @Test
    public void testFindByNonExistentEmail() {
        // Mocking the behavior of jdbcTemplate to simulate an exception scenario.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenThrow(new EmptyResultDataAccessException(1));

        // Actual method call and assertion to check if the expected exception is thrown.
        assertThrows(Exception.class, () -> {
            userDao.findByEmail("nonexistent@example.com");
        });
    }

    /**
     * Test to ensure a user can be fetched based on their ID.
     * It checks if the correct user details are returned when querying by ID.
     */
    @Test
    public void testFindById() {
        // Test data setup.
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(user);

        // Actual method call and assertion.
        User result = userDao.findById(1L);
        assertEquals(user, result);
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their ID.
     * It checks if the correct exception is thrown when querying with a non-existent ID.
     */
    @Test
    public void testFindByNonExistentId() {
        // Mocking the behavior of jdbcTemplate to simulate an exception scenario.
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        // Actual method call and assertion to check if the expected exception is thrown.
        assertThrows(Exception.class, () -> {
            userDao.findById(999L);
        });
    }

    /**
     * Test to fetch all users from the database.
     * It checks if a list of all users is returned correctly.
     */
    @Test
    public void testFindAll() {
        // Test data setup.
        User user1 = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user1.setRole("USER");

        User user2 = new User(2L, "Bob", "bob@example.com", "hashed_password", "DesignerBob", "456 Street", "9876543210");
        user2.setRole("ADMIN");

        List<User> users = Arrays.asList(user1, user2);

        // Mocking the behavior of jdbcTemplate to simulate database operations.
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(users);

        // Actual method call and assertion.
        List<User> result = userDao.findAll();
        assertEquals(users, result);
    }
}

