package com.splitmaster.com.controller;

import com.splitmaster.com.domain.UserAuthModel;
import com.splitmaster.com.service.UserService;
import com.splitmaster.com.service.dto.request.AddUsersToUgRequestDto;
import com.splitmaster.com.service.dto.request.UserProfileRequestDto;
import com.splitmaster.com.service.dto.response.UserDetailsForUg;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Api to register the user or admin")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserProfileRequestDto requestDto) {

        UserAuthModel user = userService.registerUser(requestDto);
        if(user == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists"); //409
        } else {
            return ResponseEntity.ok("User successfully registered"); // 200
        }
    }

    @Operation(summary = "Api to create user group")
    @PostMapping("/user-group/create")
    public void createUserGroup(@RequestParam("name") String name){
        userService.createUserGroup(name);
    }

    @Operation(summary = "Api to add users to the user group")
    @PostMapping("user-group/add-users")
    public void addUsersToUserGroup(@RequestBody @Valid AddUsersToUgRequestDto requestDto){
        userService.addUsersToUserGroup(requestDto);
    }

    @Operation(summary = "Api to fetch users for user group creation")
    @GetMapping("user-group/fetch-users")
    public UserDetailsForUg getUsersForUserGroupCreation(@RequestParam("id") Long userGroupId){
        return userService.getUsersForUserGroupCreation(userGroupId);
    }
}
