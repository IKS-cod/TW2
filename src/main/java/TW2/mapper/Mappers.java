package TW2.mapper;

import TW2.dto.RegisterDto;
import TW2.dto.UserDto;
import TW2.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Mappers {
    @Mapping(target = "image", ignore = true)
    UserDto toUserDto(Users users);
    @Mapping(target = "email", source = "username")
    @Mapping(target = "id", ignore = true)
        //@Mapping(target = "image", ignore = true)
    Users toUsers(RegisterDto registerDto);
}