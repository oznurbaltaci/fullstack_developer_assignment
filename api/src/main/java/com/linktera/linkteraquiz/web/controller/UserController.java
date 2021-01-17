package com.linktera.linkteraquiz.web.controller;

import com.linktera.linkteraquiz.dto.UserDTO;
import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import com.linktera.linkteraquiz.response.BookResponse;
import com.linktera.linkteraquiz.security.JwtClaimsModel;
import com.linktera.linkteraquiz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final JwtClaimsModel jwtClaimsModel;

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.getList();
    }


    @DeleteMapping("/{uuid}")
    public void deleteUser(@PathVariable("uuid") UUID uuid) {
        if (uuid == null) {
            throw new LinkteraServiceException(ErrorCode.MISSING_PARAM);
        }

        if (jwtClaimsModel.getUserType() != UserType.PERSONEL)
            throw new LinkteraServiceException(ErrorCode.FORBIDDEN);

        userService.delete(uuid);
    }


}
