package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.UserDao;
import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * Implementation of the UserService interface.
 * This class provides concrete implementations for managing users in the application.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registers a new user in the system and hashes their password.
     *
     * @param user The user entity to be registered.
     * @return The registered user with the hashed password.
     */
    @Override
    public User register(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userDao.createUser(user);
        return user;
    }

    /**
     * Authenticates a user based on their username and password.
     * If authentication is successful, the user entity is returned, otherwise null.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user entity or null if authentication fails.
     */
    @Override
    public User authenticate(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    /**
     * Updates the details of an existing user in the system.
     *
     * @param user The updated user entity.
     * @return The user entity with updated details.
     */
    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    /**
     * Deletes a user from the system based on their unique identifier.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return A boolean indicating if the deletion was successful.
     */
    @Override
    public boolean deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    /**
     * Retrieves a user entity based on their username.
     *
     * @param username The username to search for.
     * @return The user entity matching the provided username or null if not found.
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * Checks if a user exists in the system based on their username.
     *
     * @param username The username to check for existence.
     * @return A boolean indicating if a user with the provided username exists.
     */
    @Override
    public boolean existsByUsername(String username) {
        return userDao.findByUsername(username) != null;
    }

    /**
     * Checks if a user exists in the system based on their email.
     *
     * @param email The email to check for existence.
     * @return A boolean indicating if a user with the provided email exists.
     */
    @Override
    public boolean existsByEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    /**
     * Retrieves all users registered in the system.
     *
     * @return A list of all users or an empty list if no users are found.
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * Fetches a specific user based on their unique identifier.
     *
     * @param userId The unique identifier of the desired user.
     * @return The user corresponding to the provided ID or null if not found.
     */
    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }
}
