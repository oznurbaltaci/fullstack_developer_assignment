package com.linktera.linkteraquiz.dto;

import com.linktera.linkteraquiz.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID userId;
    private String username;
    private String firstName;
    private String lastName;
    private UserType userType;
}
