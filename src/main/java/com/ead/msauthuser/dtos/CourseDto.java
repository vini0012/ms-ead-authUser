package com.ead.msauthuser.dtos;

import com.ead.msauthuser.enums.CourseLevel;
import com.ead.msauthuser.enums.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;

}
