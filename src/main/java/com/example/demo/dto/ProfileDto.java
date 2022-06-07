package com.example.demo.dto;

import com.example.demo.model.Profile;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDto {
    private String message = "Get profile by id";
    private ProfileData data;

    public ProfileDto(ProfileData data) {
        this.data = data;
    }

    @Setter
    @Getter
    private static class ProfileData {
        private long profileId;
        private String name;
        private String surname;
        private String avatarLink;
        private String aboutMe;
        private String email;
        private List<String> position;
        private Date birthday;
        private ProjectManager pm;

        private static ProfileData fromProfile(Profile profile, Profile projectManager) {
            ProfileData profileData = new ProfileData();
            profileData.setProfileId(profile.getProfile_id());
            profileData.setName(profile.getName());
            profileData.setSurname(profile.getSurname());
            profileData.setAvatarLink(profile.getAvatar());
            profileData.setEmail(profile.getUser().getEmail());
            profileData.setBirthday(profile.getBirthday());
            profileData.setPosition(profile.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
            profileData.setAboutMe(profile.getRoles().stream().map(Roles::getDescription).collect(Collectors.joining(", ")));
            profileData.setPm(ProjectManager.fromProfile(projectManager));

            return profileData;
        }
    }

    @Setter
    @Getter
    private static class ProjectManager {
        private long profileId;
        private String name;
        private String surname;
        private String email;

        private static ProjectManager fromProfile(Profile profile) {
            ProjectManager projectManager = new ProjectManager();
            projectManager.setProfileId(profile.getProfile_id());
            projectManager.setName(profile.getName());
            projectManager.setSurname(profile.getSurname());
            projectManager.setEmail(profile.getUser().getEmail());
            return projectManager;
        }
    }

    public static ProfileDto fromProfile(Profile profile, Profile projectManager) {
        ProfileData profileData = ProfileData.fromProfile(profile, projectManager);

        return new ProfileDto(profileData);
    }

    public static ProfileData fromUser(User user) {
        ProfileData profileData = new ProfileData();
        profileData.setProfileId(user.getProfile().getProfile_id());
        profileData.setName(user.getProfile().getName());
        profileData.setSurname(user.getProfile().getSurname());
        profileData.setAvatarLink(user.getProfile().getAvatar());
        profileData.setEmail(user.getProfile().getUser().getEmail());
        profileData.setBirthday(user.getProfile().getBirthday());
        profileData.setPosition(user.getProfile().getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
        profileData.setAboutMe(user.getProfile().getRoles().stream().map(Roles::getDescription).collect(Collectors.joining(", ")));
        return profileData;
    }
}
