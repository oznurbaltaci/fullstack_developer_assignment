package com.linktera.linkteraquiz.model;

import com.linktera.linkteraquiz.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserType userType;

}
