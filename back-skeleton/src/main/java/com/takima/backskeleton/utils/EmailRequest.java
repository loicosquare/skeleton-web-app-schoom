package com.takima.backskeleton.utils;

import lombok.Data;

@Data
public class EmailRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String name;
    private String telephone;
    private String pays;
    private String ville;

    private String city;
    private String country;
    private String state;
    private String title;
    private String message;
    private String phone;
}

