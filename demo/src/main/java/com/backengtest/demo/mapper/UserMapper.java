package com.backengtest.demo.mapper;

import com.backengtest.demo.dto.UserDto;
import com.backengtest.demo.model.Profile;
import com.backengtest.demo.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProjectMapper.class)
public interface UserMapper {

    //@InheritInverseConfiguration
    @Mapping(target = "profileId", source = "user.profile.id")
    UserDto mapUserToDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profile", source = "profile")
    User mapUserDtoToEntity(UserDto userDto, Profile profile);
}
