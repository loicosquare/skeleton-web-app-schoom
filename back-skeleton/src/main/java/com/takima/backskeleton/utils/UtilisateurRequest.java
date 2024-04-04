package com.takima.backskeleton.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String name;
    private String telephone;
    private String pays;
    private String ville;
}
