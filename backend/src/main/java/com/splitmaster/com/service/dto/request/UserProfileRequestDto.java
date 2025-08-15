package com.splitmaster.com.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequestDto {
    private String name;
    private String username;
    private String password;
    private String role;
}
