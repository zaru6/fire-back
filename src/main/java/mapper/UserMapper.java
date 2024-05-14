package mapper;

import com.evilapp.fire.model.User;

import dtos.RegisterUserDto;
import dtos.LoginUserDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    LoginUserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(RegisterUserDto signUpDto);

}
