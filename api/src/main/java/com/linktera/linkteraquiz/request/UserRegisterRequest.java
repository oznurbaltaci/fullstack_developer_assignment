package com.linktera.linkteraquiz.request;

import com.linktera.linkteraquiz.enums.UserType;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserType userType;
}
