package com.backengtest.demo.controller;

import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.model.Profile;
import com.backengtest.demo.service.ImageCompression;
import com.backengtest.demo.service.ProfileService;
import com.backengtest.demo.service.ProjectServiceImpl;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.getProfile(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Profile> getProfile(@PathVariable String username) {
        Profile profile = profileService.getUserProfile(username);
        System.out.println("profile.getProfilePic()");
        System.out.println(profile.getProfilePic());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id,@RequestBody Profile profile) {
        Profile updatedProjectDto = profileService.updateProfile(id,profile);
        return ResponseEntity.ok().body(updatedProjectDto);
    }

    @PostMapping(path = "/image/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> uploadUserProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(profileService.updateUserProfileImage(id, file));
    }

    @PostMapping(path = "/cover/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> uploadUserProfileCover(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(profileService.updateUserProfileCover(id, file));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profilepic/{id}")
    public ResponseEntity<byte[]> getProfilePic(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.getProfile(id).getProfilePic());
    }

    @GetMapping("/profilecover/{id}")
    public ResponseEntity<byte[]> getProfileCover(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.getProfile(id).getCoverPic());
    }
}
