package com.linktera.linkteraquiz;
import static org.mockito.Mockito.*;

import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.exception.ErrorCode;
import com.linktera.linkteraquiz.exception.LinkteraServiceException;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.UserRepository;
import com.linktera.linkteraquiz.request.LoginRequest;
import com.linktera.linkteraquiz.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @Test
    public void shouldTrueIfUserNameAndPasswordIsTrue(){


        User user = User.builder().firstName("oznur").lastName("baltaci")
                .password("kiljh").username("oznurba")
                .userType(UserType.PERSONEL).userId(UUID.randomUUID()).build();

        when(userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword())).thenReturn(Optional.ofNullable(user));


        //AssertEquals(Mockito.when(userService.login(new LoginRequest(user.getUsername(), user.getPassword()))));




    }
}
