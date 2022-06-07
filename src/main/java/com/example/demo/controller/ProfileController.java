package com.example.demo.controller;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UpdateCommentRequestDto;
import com.example.demo.dto.UpdateProfileDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Profile;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ProfileController {

    private final ProfileRepository profileRepository;
    private RolesRepository rolesRepository;
    private UserRepository userRepository;

    /*@GetMapping("/profile/{profileId}")
    public ProfileDto getProfileInformation(@PathVariable long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> {
            throw new RuntimeException("Profile is not found");
        });

        return ProfileDto.fromProfile(profile);
    }*/

    @PutMapping("/profile/{profileId}")
    public ResponseDto updateProfile(@RequestBody UpdateProfileDto updateProfileDto, @PathVariable Long profileId){
        try {
            Profile profile = profileRepository.findById(profileId).get();
            profile.setName(updateProfileDto.getName());
            profile.setSurname(updateProfileDto.getSurname());
            profile.setBirthday(updateProfileDto.getBirthdayDate());
            User user = profile.getUser();
            user.setEmail(updateProfileDto.getEmail());
            List<Roles> roles = profile.getRoles();
            for(Roles r : roles){
                r.setDescription(updateProfileDto.getAboutMe());
                rolesRepository.save(r);
            }
            userRepository.save(user);
            profileRepository.save(profile);
            return new ResponseDto("edit profile", true, null);
        } catch (RuntimeException e){
            return new ResponseDto("profile not found by id", false, null);
        }
    }
}
