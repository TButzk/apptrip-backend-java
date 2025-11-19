package unisinos.tripverse.map;

import org.springframework.stereotype.Component;
import unisinos.tripverse.model.user.User;
import unisinos.tripverse.model.user.UserDto;
import unisinos.tripverse.model.user.UserLoginDto;

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
