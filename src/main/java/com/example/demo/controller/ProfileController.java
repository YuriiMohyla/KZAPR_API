package com.example.demo.controller;

import com.example.demo.dto.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProfileController {

    private final ProfileRepository profileRepository;

    @GetMapping("/profile/{profileId}")
    public ProfileDto getProfileInformation(@PathVariable long profileId) {
        Profile profile = profileRepository.getById(profileId);

        return ProfileDto.fromProfile(profile);
    }
}
