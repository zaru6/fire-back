package mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evilapp.fire.model.User;

import dtos.SignUpDto;
import dtos.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
