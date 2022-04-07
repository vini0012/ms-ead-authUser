package com.ead.msauthuser.repositories;

import com.ead.msauthuser.models.UserCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {
}
