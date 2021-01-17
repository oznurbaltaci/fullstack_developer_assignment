package com.linktera.linkteraquiz.web.controller;

import com.linktera.linkteraquiz.model.TokenModel;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.LoginRequest;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import com.linktera.linkteraquiz.security.JwtClaimsModel;
import com.linktera.linkteraquiz.security.JwtGenerator;
import com.linktera.linkteraquiz.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtClaimsModel jwtClaimsModel;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation("Kullanıcı kayıt işlemini gerçekleştirir.")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation("Kullanıcı giriş işlemini gerçekleştirir")
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest);
        return new ResponseEntity<>(new TokenModel("Bearer " + jwtGenerator.generate(user), user.getUserType().ordinal()), HttpStatus.OK);
    }

}
