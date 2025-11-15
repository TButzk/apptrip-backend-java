package unisinos.tripverse.model.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserDto {

    private String name;

    private String email;

}
