package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.UserDao;
import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the UserDetailsService interface to provide custom user authentication for the application.
 * It retrieves user details based on the provided username.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    /**
     * Constructs a new CustomUserDetailsService with the specified UserDao.
     *
     * @param userDao the DAO responsible for User entity operations.
     */
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Loads a user entity based on the provided username.
     *
     * @param username the username of the user to be retrieved.
     * @return a UserDetails object containing the user's information and authorities.
     * @throws UsernameNotFoundException if the user is not found in the system.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
            authorities.add(authority);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), authorities);
    }

}
