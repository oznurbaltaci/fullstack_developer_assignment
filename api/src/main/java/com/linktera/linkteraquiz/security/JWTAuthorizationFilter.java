package com.linktera.linkteraquiz.security;


import com.linktera.linkteraquiz.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static com.linktera.linkteraquiz.security.SecurityConstants.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JwtClaimsModel jwtClaimsModel;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JwtClaimsModel jwtClaimsModel) {
        super(authManager);
        this.jwtClaimsModel = jwtClaimsModel;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String token;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            token = header.replace(TOKEN_PREFIX, "");

            if(!token.equals("null") && !token.equals("undefined"))
                SecurityContextHolder.getContext().setAuthentication(validate(token));
        }

        chain.doFilter(req, res);
    }

    public UsernamePasswordAuthenticationToken validate(String token){
        Claims body = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        jwtClaimsModel.setUserId(UUID.fromString(body.get("userId").toString()));
        jwtClaimsModel.setUserType(UserType.valueOf(body.get("userType").toString()));
        jwtClaimsModel.setUsername(body.getSubject());

        return new UsernamePasswordAuthenticationToken(body.getSubject(), null, new ArrayList<>());
    }

}