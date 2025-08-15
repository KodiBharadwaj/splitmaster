package com.splitmaster.com.service;

import com.splitmaster.com.domain.UserAuthModel;
import com.splitmaster.com.service.dto.request.AddUsersToUgRequestDto;
import com.splitmaster.com.service.dto.request.UserProfileRequestDto;
import com.splitmaster.com.service.dto.response.UserDetailsForUg;
import jakarta.validation.Valid;

public interface UserService {
    UserAuthModel registerUser(UserProfileRequestDto requestDto);

    void createUserGroup(String name);

    void addUsersToUserGroup(@Valid AddUsersToUgRequestDto requestDto);

    UserDetailsForUg getUsersForUserGroupCreation(Long userGroupId);
}
