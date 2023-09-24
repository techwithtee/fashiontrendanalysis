package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Provides a JDBC-based implementation for user-related database operations.
 * Implements the UserDao interface.
 *
 * @author Your Name
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Defines the RowMapper for mapping rows of the ResultSet to the User object.
     */
    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("designer_name"),
                    rs.getString("address"),
                    rs.getString("phone")
            );
        }
    };

    /**
     * Fetches a user from the database based on their username.
     *
     * @param username Username of the user to fetch.
     * @return User object if found, otherwise null.
     */
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM fashion_user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, username);
    }

    /**
     * Fetches a user from the database based on their email address.
     *
     * @param email Email address of the user to fetch.
     * @return User object if found, otherwise null.
     */
    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM fashion_user WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, email);
    }

    /**
     * Inserts a new user record into the database.
     *
     * @param user User object containing details to be inserted.
     * @return ID of the newly inserted user.
     */
    @Override
    public Long createUser(User user) {
        String sql = "INSERT INTO fashion_user (username, email, password_hash, designer_name, address, phone) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getDesignerName(), user.getAddress(), user.getPhone());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    /**
     * Updates an existing user's details in the database.
     *
     * @param user User object containing the updated details.
     * @return Updated User object.
     */
    @Override
    public User updateUser(User user) {
        String sql = "UPDATE fashion_user SET username = ?, email = ?, password_hash = ?, designer_name = ?, address = ?, phone = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getDesignerName(), user.getAddress(), user.getPhone(), user.getUserId());
        return findById(user.getUserId());
    }

    /**
     * Deletes a user record from the database based on their ID.
     *
     * @param userId ID of the user to be deleted.
     * @return True if deletion was successful, false otherwise.
     */
    @Override
    public boolean deleteUser(Long userId) {
        String sql = "DELETE FROM fashion_user WHERE user_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected > 0;
    }

    /**
     * Fetches a user from the database based on their unique ID.
     *
     * @param userId Unique ID of the user to fetch.
     * @return User object if found, otherwise null.
     */
    @Override
    public User findById(Long userId) {
        String sql = "SELECT * FROM fashion_user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
    }
}
