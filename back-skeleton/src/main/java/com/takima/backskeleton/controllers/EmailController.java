package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.ContactForm;
import com.takima.backskeleton.models.Utilisateur;
import com.takima.backskeleton.services.UtilisateurService;
import com.takima.backskeleton.utils.EmailRequest;
import com.takima.backskeleton.utils.LoginRequest;
import com.takima.backskeleton.utils.UtilisateurRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequestMapping("emails")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final UtilisateurService utilisateurService;

    /*@PostMapping("/sendEmail")
    public ResponseEntity<Boolean> sendMail(@RequestParam UtilisateurRequest utilisateur, @RequestParam ContactForm contactForm) {
        boolean emailSent = utilisateurService.sendEmail(utilisateur, contactForm);
        return new ResponseEntity<>(emailSent, HttpStatus.OK);
    }*/

    @PostMapping("/sendEmail")
    public ResponseEntity<Boolean> sendMail(@RequestBody EmailRequest params) {
        System.out.println("params"+params);
        UtilisateurRequest utilisateur = getUtilisateurRequest(params);

        ContactForm contactForm = new ContactForm();
        contactForm.setCity(params.getCity());
        contactForm.setEmail(params.getEmail());
        contactForm.setCountry(params.getCountry());
        contactForm.setState(params.getState());
        contactForm.setTitle(params.getTitle());
        contactForm.setMessage(params.getMessage());
        contactForm.setPhone(params.getPhone());

        boolean emailSent = utilisateurService.sendEmail(utilisateur, contactForm);
        return new ResponseEntity<>(emailSent, HttpStatus.OK);
    }

    private static UtilisateurRequest getUtilisateurRequest(EmailRequest params) {
        UtilisateurRequest utilisateur = new UtilisateurRequest();
        utilisateur.setId(params.getId());
        utilisateur.setUsername(params.getUsername());
        utilisateur.setEmail(params.getEmail());
        utilisateur.setPassword(params.getPassword());
        utilisateur.setProfilePicture(params.getProfilePicture());
        utilisateur.setName(params.getName());
        utilisateur.setTelephone(params.getTelephone());
        utilisateur.setPays(params.getPays());
        utilisateur.setVille(params.getVille());
        return utilisateur;
    }

}
