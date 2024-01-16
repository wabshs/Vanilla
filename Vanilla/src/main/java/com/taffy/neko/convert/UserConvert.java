package com.taffy.neko.convert;


import com.taffy.neko.entity.User;
import com.taffy.neko.entity.dto.UserRegisterReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User toUser(UserRegisterReqDTO reqDTO);
}
