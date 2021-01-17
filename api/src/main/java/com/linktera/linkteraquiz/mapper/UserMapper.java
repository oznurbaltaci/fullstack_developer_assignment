package com.linktera.linkteraquiz.mapper;

import com.linktera.linkteraquiz.dto.UserDTO;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.UserRegisterRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User toUserAccountFromRegisterRequest(UserRegisterRequest userRegisterRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDTO toUserResponseMapper(User user);


}
