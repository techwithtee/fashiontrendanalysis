package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.UserDao;
import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userDao.createUser(user);
        return user;
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
