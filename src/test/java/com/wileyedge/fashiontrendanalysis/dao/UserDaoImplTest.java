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

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDaoImpl userDao;

    @Test
    public void testCreateUser() {
        User user = new User(null, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        when(jdbcTemplate.update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPasswordHash()), eq(user.getDesignerName()), eq(user.getAddress()), eq(user.getPhone()), eq(user.getRole()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);

        Long resultId = userDao.createUser(user);

        assertEquals(1L, resultId);
    }

    @Test
    public void testFindByUsername() {
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(user);

        User result = userDao.findByUsername("Alice");
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1L, "AliceUpdated", "aliceupdated@example.com", "hashed_password_updated", "DesignerAliceUpdated", "1234 Street", "12345678901");
        user.setRole("ADMIN");

        when(jdbcTemplate.update(anyString(), eq(user.getUsername()), eq(user.getEmail()), eq(user.getPasswordHash()), eq(user.getDesignerName()), eq(user.getAddress()), eq(user.getPhone()), eq(user.getRole()), eq(user.getUserId()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(user);

        User updatedUser = userDao.updateUser(user);
        assertEquals(user, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);

        boolean result = userDao.deleteUser(1L);
        assertEquals(true, result);
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their username.
     */
    @Test
    public void testFindByNonExistentUsername() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            userDao.findByUsername("NonExistentUsername");
        });
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their ID.
     */
    @Test
    public void testDeleteNonExistentUser() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(0);

        boolean result = userDao.deleteUser(999L);
        assertEquals(false, result);
    }

    /**
     * Test to ensure a user can be fetched based on their email.
     */
    @Test
    public void testFindByEmail() {
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(user);

        User result = userDao.findByEmail("alice@example.com");
        assertEquals(user, result);
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their email.
     */
    @Test
    public void testFindByNonExistentEmail() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            userDao.findByEmail("nonexistent@example.com");
        });
    }

    /**
     * Test to ensure a user can be fetched based on their ID.
     */
    @Test
    public void testFindById() {
        User user = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user.setRole("USER");

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(user);

        User result = userDao.findById(1L);
        assertEquals(user, result);
    }

    /**
     * Test to handle scenarios where the requested user doesn't exist based on their ID.
     */
    @Test
    public void testFindByNonExistentId() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            userDao.findById(999L);
        });
    }

    /**
     * Test to fetch all users from the database.
     */
    @Test
    public void testFindAll() {
        User user1 = new User(1L, "Alice", "alice@example.com", "hashed_password", "DesignerAlice", "123 Street", "1234567890");
        user1.setRole("USER");

        User user2 = new User(2L, "Bob", "bob@example.com", "hashed_password", "DesignerBob", "456 Street", "9876543210");
        user2.setRole("ADMIN");

        List<User> users = Arrays.asList(user1, user2);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(users);

        List<User> result = userDao.findAll();
        assertEquals(users, result);
    }
}

