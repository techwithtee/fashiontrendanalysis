package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

/**
 * Defines the CRUD operations for the User entity.
 */
public interface UserDao {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the User if found, null otherwise
     */
    User findByUsername(String username);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return the User if found, null otherwise
     */
    User findByEmail(String email);

    /**
     * Inserts a new user record into the database.
     *
     * @param user the user object to insert
     * @return the generated ID of the newly created user
     */
    Long createUser(User user);

    /**
     * Updates a user's details in the database.
     *
     * @param user the user object containing the updated details
     * @return the updated User object
     */
    User updateUser(User user);

    /**
     * Deletes a user from the database.
     *
     * @param userId the ID of the user to delete
     * @return true if the user was deleted successfully, false otherwise
     */
    boolean deleteUser(Long userId);

    /**
     * Finds a user by their unique ID.
     *
     * @param userId the ID of the user to search for
     * @return the User object corresponding to the given ID if found; null otherwise
     */
    User findById(Long userId);

    /**
     * Retrieves a list of all users from the database.
     *
     * @return a list containing all User objects in the database, or an empty list if no users are found
     */
    List<User> findAll();
}

