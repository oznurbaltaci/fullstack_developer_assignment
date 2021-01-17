package com.linktera.linkteraquiz.service;

import com.linktera.linkteraquiz.dto.UserDTO;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.request.LoginRequest;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import com.linktera.linkteraquiz.service.base.BaseService;

import java.util.List;
import java.util.UUID;

public interface UserService {
     // User Login, returns JWT token
     void register(UserRegisterRequest userRegisterRequest);
     User login(LoginRequest loginRequest);
     void delete(UUID uuid);
     List<UserDTO> getList();

}
