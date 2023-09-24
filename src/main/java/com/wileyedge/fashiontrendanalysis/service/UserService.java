package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.User;

public interface UserService {
    User register(User user);
    User authenticate(String username, String password);
    User updateUser(User user);
    boolean deleteUser(Long userId);
    User findByUsername(String username);
}
