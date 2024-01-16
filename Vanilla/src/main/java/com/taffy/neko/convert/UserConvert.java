package com.taffy.neko.convert;


import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    //不映射emailCode
    default User toUser(UserRegisterReqDTO reqDTO) {
        User user = new User();
        user.setUserName(reqDTO.getUserName());
        user.setNickName(reqDTO.getNickName());
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        user.setPhoneNumber(reqDTO.getPhoneNumber());
        user.setSex(reqDTO.getSex());
        user.setAvatar(reqDTO.getAvatar());
        return user;
    }

}
