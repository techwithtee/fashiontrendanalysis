package com.wileyedge.fashiontrendanalysis.model;

import lombok.Data;

/**
 * Represents a user in the system.
 * Contains fields that map to the 'fashion_user' table in the database.
 *
 * @author Your Name
 * @version 1.0
 */

public class User {

    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String designerName;
    private String address;
    private String phone;
    private String role;

    /**
     * Constructs a new user instance with specified details.
     *
     * @param userId Unique identifier of the user.
     * @param username Username of the user.
     * @param email Email address of the user.
     * @param passwordHash Hashed password of the user.
     * @param designerName Designer name associated with the user (if any).
     * @param address Address of the user.
     * @param phone Contact phone number of the user.
     */
    public User(Long userId, String username, String email, String passwordHash, String designerName, String address, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.designerName = designerName;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters for all fields

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Add getters and setters for the role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
