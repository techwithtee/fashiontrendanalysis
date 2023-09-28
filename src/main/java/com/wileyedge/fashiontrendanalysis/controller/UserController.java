package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.User;
import com.wileyedge.fashiontrendanalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user.
     * @param user User details for registration.
     * @return Response message indicating registration status.
     * @apiEndpoint POST http://localhost:6363/api/register
     */
    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername()) || userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Username or Email already taken!", HttpStatus.BAD_REQUEST);
        }
        userService.register(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    /**
     * Login page endpoint. Intended to be handled by a frontend client.
     * @return Authentication prompt message.
     * @apiEndpoint GET http://localhost:6363/login
     */
    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return new ResponseEntity<>("Please authenticate.", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Authenticate a user based on provided credentials.
     * @param credentials Map containing the username and password.
     * @return Response message indicating authentication status.
     * @apiEndpoint POST http://localhost:6363/api/login
     */
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        User user = userService.findByUsername(credentials.get("username"));
        if (user == null || !passwordEncoder.matches(credentials.get("password"), user.getPasswordHash())) {
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("User authenticated successfully!", HttpStatus.OK);
    }

    /**
     * Retrieve a list of all registered users.
     * This endpoint is intended for admin-only access.
     * @return List of all users.
     * @apiEndpoint GET http://localhost:6363/api/users
     */
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Retrieve details of a specific user based on their ID.
     * This endpoint is intended for admin-only access.
     * @param userId ID of the user.
     * @return User details.
     * @apiEndpoint GET http://localhost:6363/api/users/{userId}
     */
    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Update details of a specific user based on their ID.
     * This endpoint is intended for admin-only access.
     * @param userId ID of the user.
     * @param userUpdates Updated user details.
     * @return Response message indicating update status.
     * @apiEndpoint PUT http://localhost:6363/api/users/{userId}
     */
    @PutMapping("/api/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User userUpdates) {
        User existingUser = userService.findById(userId);
        if (existingUser == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        // Update fields as necessary
        existingUser.setUsername(userUpdates.getUsername());
        existingUser.setEmail(userUpdates.getEmail());
        existingUser.setDesignerName(userUpdates.getDesignerName());
        existingUser.setAddress(userUpdates.getAddress());
        existingUser.setPhone(userUpdates.getPhone());
        userService.updateUser(existingUser);

        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }

    /**
     * Delete a specific user based on their ID.
     * This endpoint is intended for admin-only access.
     * @param userId ID of the user.
     * @return Response message indicating deletion status.
     * @apiEndpoint DELETE http://localhost:6363/api/users/{userId}
     */
    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        if (userService.deleteUser(userId)) {
            return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }
}
