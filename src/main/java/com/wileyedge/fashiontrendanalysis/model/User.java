package com.wileyedge.fashiontrendanalysis.model;

/**
 * Represents a user in the system.
 * Contains fields that map to the 'fashion_user' table in the database.
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

    /**
     * @return the unique identifier of the user.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     * @param userId The unique identifier to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hashed password of the user.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the hashed password for the user.
     * @param passwordHash The hashed password to set.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the designer name associated with the user.
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * Sets the designer name for the user.
     * @param designerName The designer name to set.
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    /**
     * @return the address of the user.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address for the user.
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contact phone number of the user.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the contact phone number for the user.
     * @param phone The contact phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role for the user.
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
