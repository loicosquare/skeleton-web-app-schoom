package com.takima.backskeleton.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contact")
public class ContactForm {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String country;
    private String state;
    private String email;
    private String title;
    private String message;
    private String phone;
}
