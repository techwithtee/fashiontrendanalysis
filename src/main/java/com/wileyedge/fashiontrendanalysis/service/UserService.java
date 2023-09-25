package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.User;

import java.util.List;

public interface UserService {
    User register(User user);
    User authenticate(String username, String password);
    User updateUser(User user);
    boolean deleteUser(Long userId);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> getAllUsers();
    User findById(Long userId);
}
