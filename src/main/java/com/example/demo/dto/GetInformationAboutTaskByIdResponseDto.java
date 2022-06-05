package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/*{
  "message": "post data",
  "data": {
    id: 1,
    title: '',
    description: '' || NULL,
    status: {
      statusId: 1,
      name: '',
      color: '' || NULL,
    },
    project: {
      projectId: 1,
      name: '',
    },
    assignTo: {
      profiledId: 1,
      name: '',
      surname: '',
      email: '',
    },
    createdBy: {
      profileId: 1,
      name: '',
      surname: '',
      email: '',
    },
    inspector: {
      profileId: 1,
      createdByName: '',
      createdBySurname: '',
      email: '',
    },
    start: '',
    end: '',
    color: '' || NULL,
    attached: [
      {
        name: '' || null,
        link: '',
        type: 'link' || 'document',
      },
      ...
    ],
  },
  "success": true,
}*/

@AllArgsConstructor
@Getter
@ToString
public class GetInformationAboutTaskByIdResponseDto {

    private String message;
    private GetInformationAboutTaskByIdResponseDto.Data dataList;
    private boolean success;

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Data {

        private Long id;
        private String title;
        private String description;
        private Status status;
        private Project project;
        private AssignTo assignTo;
       /* private CreatedBy createdBy;
        private Inspector inspector;*/
        private Timestamp start;
        private Timestamp end;
        private String color;
        private List<Attached> attached;

        @AllArgsConstructor
        @Getter
        @ToString
        private static class Status {

            private Long statusId;
            private String name;
            private String color;

        }

        @AllArgsConstructor
        @Getter
        @ToString
        private static class Project {

            private Long projectId;
            private String name;

        }

        @AllArgsConstructor
        @Getter
        @ToString
        private static class AssignTo {

            private Long profileId;
            private String name;
            private String surname;
            private String email;

        }

       /* @AllArgsConstructor
        @Getter
        @ToString
        private static class CreatedBy {

            private Long profileId;
            private String name;
            private String surname;
            private String email;

        }

        @AllArgsConstructor
        @Getter
        @ToString
        private static class Inspector {

            private Long profileId;
            private String createdByName;
            private String createdBySurname;
            private String email;

        }*/

        @AllArgsConstructor
        @Getter
        @ToString
        public static class Attached {

            private Long name;
            private String link;
            private String type;

        }

        public static Status toStatus(com.example.demo.model.Status status){
            return new Status(status.getStatus_id(), status.getName(), status.getColor());
        }

        public static Project toProject(com.example.demo.model.Project project){
            return new Project(project.getProject_id(), project.getTitle());
        }

        public static AssignTo toAssignTo(Profile profile, User user){
            return new AssignTo(profile.getProfile_id(), profile.getName(), profile.getSurname(), user.getEmail());
        }

       /* public static CreatedBy toCreatedBy(Profile profile, User user){
            return new CreatedBy(profile.getProfile_id(), profile.getName(), profile.getSurname(), user.getEmail());
        }

        public static Inspector toInspector(Profile profile, User user){
            return new Inspector(profile.getProfile_id(), profile.getName(), profile.getSurname(), user.getEmail());
        }*/

        public static Attached toAttached(Attachment attachment){
            return new Attached(attachment.getAttachment_id(), attachment.getLink(), attachment.getType());
        }

    }

}
