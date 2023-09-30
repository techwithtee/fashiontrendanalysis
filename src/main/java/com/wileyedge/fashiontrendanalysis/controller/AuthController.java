package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.dao.UserDao;
import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Display the login page.
     * @return The login page view.
     * @apiEndpoint GET http://localhost:6363/auth/login
     */

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login endpoint hit. Frontend not implemented yet.");
    }

    /**
     * Display the registration form.
     * @return The registration form view.
     * @apiEndpoint GET http://localhost:6363/auth/register
     */

    @GetMapping("/register")
    public ResponseEntity<String> registerForm() {
        return ResponseEntity.ok("Registration endpoint hit. Frontend not implemented yet.");
    }

    /**
     * Process the registration form submission and create a new user.
     * @param user User details from the registration form.
     * @return Redirect to the login page upon successful registration.
     * @apiEndpoint POST http://localhost:6363/auth/register
     */

    @PostMapping("/register")
    public ResponseEntity<String> register(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userDao.createUser(user);
        return ResponseEntity.ok("User registered successfully. Login frontend not implemented yet.");
    }
}
