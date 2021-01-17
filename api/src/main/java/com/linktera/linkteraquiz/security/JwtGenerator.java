package com.linktera.linkteraquiz.security;



import com.linktera.linkteraquiz.model.TokenModel;
import com.linktera.linkteraquiz.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private String jwtSecret = "jukulaminuo";

    public String generate(User user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());

        claims.put("userType", user.getUserType().toString());
        claims.put("userId", user.getUserId().toString());
        System.out.println(user.getUserId().toString());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes(Charset.forName("UTF-8")))
                .compact();

        return token;

    }

    public String getTokenSubject(String token) {
        //exception
            Claims claims = Jwts.parser().setSigningKey(jwtSecret.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token).getBody();
            return claims.getSubject();

    }

//    public static String generate(UserAccount userAccount) {
//
//
//        Claims claims = Jwts.claims()
//                .setSubject(jwtUser.getPhoneNumber());
//        claims.put("userId", String.valueOf(jwtUser.getId()));
//
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, "ASFfdg35wee1959")
//                .compact();
//    }
}
