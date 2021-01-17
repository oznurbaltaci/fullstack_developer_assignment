package com.linktera.linkteraquiz.security;

import com.linktera.linkteraquiz.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("singleton")
public class JwtClaimsModel {
    private UUID userId;
    private String username;
    private UserType userType;
}