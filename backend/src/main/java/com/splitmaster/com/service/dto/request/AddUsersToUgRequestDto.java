package com.splitmaster.com.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUsersToUgRequestDto {
    private Long userGroupId;
    private List<Long> userIds;
}
