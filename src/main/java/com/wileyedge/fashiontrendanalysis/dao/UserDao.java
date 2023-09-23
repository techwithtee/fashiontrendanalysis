package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User findByUsername(String username);
    User findById(Long id);
    void save(User user);
    void update(User user);
}
