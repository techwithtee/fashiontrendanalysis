package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.User;

import java.util.List;

public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param user The user entity to be registered.
     * @return The registered user entity with any system-generated fields populated.
     */
    User register(User user);

    /**
     * Authenticates a user based on their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user entity if the credentials are valid, otherwise null.
     */
    User authenticate(String username, String password);

    /**
     * Updates the details of an existing user.
     *
     * @param user The updated user entity.
     * @return The updated user entity with changes reflected.
     */
    User updateUser(User user);

    /**
     * Deletes a user based on their unique identifier.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return A boolean indicating if the deletion was successful.
     */
    boolean deleteUser(Long userId);

    /**
     * Retrieves a user entity based on their username.
     *
     * @param username The username to search for.
     * @return The user entity matching the provided username or null if not found.
     */
    User findByUsername(String username);

    /**
     * Checks if a user exists based on their username.
     *
     * @param username The username to check for existence.
     * @return A boolean indicating if a user with the provided username exists.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists based on their email.
     *
     * @param email The email to check for existence.
     * @return A boolean indicating if a user with the provided email exists.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves all users registered in the system.
     *
     * @return A list of all users or an empty list if no users are found.
     */
    List<User> getAllUsers();

    /**
     * Fetches a specific user based on their unique identifier.
     *
     * @param userId The unique identifier of the desired user.
     * @return The user corresponding to the provided ID or null if not found.
     */
    User findById(Long userId);
}

