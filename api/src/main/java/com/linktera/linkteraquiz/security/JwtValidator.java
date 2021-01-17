package com.linktera.linkteraquiz.security;


import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.UUID;

@Component
public class JwtValidator {

    private String secret = "jukulaminuo";

    public User validate(String token){
        Claims body = Jwts.parser()
                .setSigningKey(secret.getBytes(Charset.forName("UTF-8")))
                .parseClaimsJws(token)
                .getBody();

        User user = new User();

        user.setUserId(UUID.fromString(body.get("userId").toString()));
        user.setUserType(UserType.valueOf(body.get("userType").toString()));

        return user;
    }

}










