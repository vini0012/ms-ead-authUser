package com.ead.msauthuser.services.impl;

import com.ead.msauthuser.repositories.UserCourseRepository;
import com.ead.msauthuser.services.UserCourseService;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;

    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }
}
