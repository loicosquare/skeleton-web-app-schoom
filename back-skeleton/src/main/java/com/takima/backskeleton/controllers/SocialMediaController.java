package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.SocialMedia;
import com.takima.backskeleton.services.SocialMediaService;
import com.takima.backskeleton.utils.SocialMediaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("socials-medias")
@RestController
@RequiredArgsConstructor
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @PostMapping("/add")
    public ResponseEntity<SocialMedia> saveSocialMedia(@RequestParam Long userId,  @RequestBody SocialMediaRequest socialMediaRequest) {
        SocialMedia savedSocialMedia = socialMediaService.saveSocialMedia(userId, socialMediaRequest);
        return new ResponseEntity<>(savedSocialMedia, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialMedia> updateSocialMedia(@RequestBody SocialMedia socialMedia) {
        SocialMedia updatedSocialMedia = socialMediaService.updateSocialMedia(socialMedia);
        return new ResponseEntity<>(updatedSocialMedia, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SocialMedia>> getAllSocialMediaByUserId(@PathVariable Long userId) {
        List<SocialMedia> socialMediaList = socialMediaService.getAllSocialMediaByUserId(userId);
        return new ResponseEntity<>(socialMediaList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialMedia(@PathVariable Long id) {
        socialMediaService.deleteSocialMedia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}