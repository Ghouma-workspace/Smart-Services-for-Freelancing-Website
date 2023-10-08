package com.backengtest.demo.service;


import com.backengtest.demo.exception.SpringFreelanciniException;
import com.backengtest.demo.model.Profile;
import com.backengtest.demo.model.User;
import com.backengtest.demo.repository.ProfileRepository;
import com.backengtest.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile getProfile(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new SpringFreelanciniException("User with id " + id + " is not found"));
    }

    @Transactional
    public Profile updateProfile(Long id,Profile profile){
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new SpringFreelanciniException("Project with id " + id + " not found"));
        existingProfile.setBio(profile.getBio());
        existingProfile.setExperience(profile.getExperience());
        existingProfile.setSkills(profile.getSkills());
        existingProfile.setAboutMe(profile.getAboutMe());
        existingProfile.setProfilePic(profile.getProfilePic());
        existingProfile.setCoverPic(profile.getCoverPic());
        return profileRepository.save(existingProfile);
    }

    @Transactional
    public Profile updateUserProfileImage(Long id, MultipartFile file) throws IOException {
        Profile userProfile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User profile not found"));
        userProfile.setProfilePic(file.getBytes());
        profileRepository.save(userProfile);
        return userProfile;
    }

    @Transactional
    public void deleteProfile(Long id) { profileRepository.deleteById(id); }

    @Transactional
    public Profile getUserProfile(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        Profile profile = user.getProfile();/*
        if (profile.getProfilePic()!=null) {
            profile.setProfilePic(ImageCompression.decompressImage(profile.getProfilePic()));
        }*/
        return profile;
    }

    @Transactional
    public Profile updateUserProfileCover(Long id, MultipartFile file) throws IOException {
        Profile userProfile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User profile not found"));
        userProfile.setCoverPic(file.getBytes());
        profileRepository.save(userProfile);
        return userProfile;
    }


}
