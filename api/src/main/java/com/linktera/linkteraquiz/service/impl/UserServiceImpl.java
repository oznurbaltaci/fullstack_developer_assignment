package com.linktera.linkteraquiz.service.impl;

import com.linktera.linkteraquiz.dto.RentedBookDTO;
import com.linktera.linkteraquiz.dto.UserDTO;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.mapper.UserMapper;
import com.linktera.linkteraquiz.model.RentedBook;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.UserRepository;
import com.linktera.linkteraquiz.request.LoginRequest;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import com.linktera.linkteraquiz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void register(UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest.getUsername() == null || userRepository.checkByUsername(userRegisterRequest.getUsername()))
            throw new LinkteraServiceException(ErrorCode.USERNAME_EXIST);

        User user;
        user = userMapper.toUserAccountFromRegisterRequest(userRegisterRequest);
        user.setUserId(UUID.randomUUID());
        userRepository.save(user);

    }

    public User login(LoginRequest loginRequest) {
        return userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()).orElseThrow(IllegalArgumentException::new);
    }

    public List<UserDTO> getList() {
        return convertToDTOList(userRepository.getList());
    }

    @Override
    public void delete(UUID uuid) {
        userRepository.delete(uuid);
    }

    public UserDTO convertToDTO(User user) {
        return userMapper.toUserResponseMapper(user);
    }
    public List<UserDTO> convertToDTOList(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();

        users.forEach(x -> {
            userDTOS.add(convertToDTO(x));
        });

        return userDTOS;
    }


}
