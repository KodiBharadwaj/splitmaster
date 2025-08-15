package com.splitmaster.com.service.impl;

import com.splitmaster.com.domain.UserAuthModel;
import com.splitmaster.com.domain.UserGroup;
import com.splitmaster.com.domain.UserGroupAssociation;
import com.splitmaster.com.domain.UserProfile;
import com.splitmaster.com.repository.UserGroupAssociationRepository;
import com.splitmaster.com.repository.UserGroupRepository;
import com.splitmaster.com.repository.UserProfileRepository;
import com.splitmaster.com.repository.UserRepository;
import com.splitmaster.com.service.UserService;
import com.splitmaster.com.service.dto.request.AddUsersToUgRequestDto;
import com.splitmaster.com.service.dto.request.UserProfileRequestDto;
import com.splitmaster.com.service.dto.response.UserDetailsForUg;
import com.splitmaster.com.utils.enums.UserRoles;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import static com.splitmaster.com.utils.StringUtils.userRoleAssociation;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserGroupAssociationRepository userGroupAssociationRepository;

//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository,
                           UserProfileRepository userProfileRepository,
                           UserGroupRepository userGroupRepository,
                           UserGroupAssociationRepository userGroupAssociationRepository){
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userGroupRepository = userGroupRepository;
        this.userGroupAssociationRepository = userGroupAssociationRepository;
    }

    @Override
    @Transactional
    public UserAuthModel registerUser(UserProfileRequestDto requestDto) {
        if(userRepository.getUserDetailsByUsername(requestDto.getUsername()) != null){
            return null;
        }

        UserAuthModel userAuthModel = new UserAuthModel();
        userAuthModel.setUsername(requestDto.getUsername());
//        userAuthModel.setPassword(encoder.encode(requestDto.getPassword()));
        userAuthModel.setPassword(requestDto.getPassword());
        userAuthModel.setDeleted(false);
        userAuthModel.setBlocked(false);

        int roleId = 0;
        for(Map.Entry<Integer, String> it: userRoleAssociation.entrySet()){
            if(it.getValue().equalsIgnoreCase(requestDto.getRole())){
                roleId = it.getKey();
            }
        }
        userAuthModel.setRoleId(roleId);
        UserAuthModel user = userRepository.save(userAuthModel);

        if(!userRoleAssociation.get(roleId).equalsIgnoreCase(UserRoles.ADMIN.name())) {
            saveUserProfileDetails(user.getId(), requestDto);
        }
        return user;
    }

    @Override
    @Transactional
    public void createUserGroup(String name) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(name);
        userGroup.setCreatedBy(1L);
        userGroup.setCreatedDateTime(LocalDateTime.now());
        userGroup.setActive(true);
        userGroupRepository.save(userGroup);
    }

    @Override
    @Transactional
    public void addUsersToUserGroup(AddUsersToUgRequestDto requestDto) {
        requestDto.getUserIds().forEach(userId -> {
            UserGroupAssociation userGroupAssociation = new UserGroupAssociation();
            userGroupAssociation.setUserGroupId(requestDto.getUserGroupId());
            userGroupAssociation.setUserId(userId);
            userGroupAssociation.setJoinDate(LocalDateTime.now());
            userGroupAssociationRepository.save(userGroupAssociation);
        });
    }

    @Override
    public UserDetailsForUg getUsersForUserGroupCreation(Long userGroupId) {
        return null;
    }

    private void saveUserProfileDetails(Long userId, UserProfileRequestDto requestDto){
        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setName(requestDto.getName());
        profile.setCreatedDateTime(LocalDateTime.now());
        userProfileRepository.save(profile);
    }
}
