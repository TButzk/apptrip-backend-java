package unisinos.apptrip.map;

import org.springframework.stereotype.Component;
import unisinos.apptrip.model.User;
import unisinos.apptrip.dto.UserDto;
import unisinos.apptrip.dto.UserLoginDto;

@Component
public class UserMapper {

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public UserLoginDto toDto(User user, String token){
        return UserLoginDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .token(token)
                .build();
    }
}

