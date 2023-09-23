package com.wileyedge.fashiontrendanalysis.model;

public class User {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash; // Remember: Do not expose hashed passwords.
    private String designerName;
    private String address;
    private String phone;
    // getters and setters
}
