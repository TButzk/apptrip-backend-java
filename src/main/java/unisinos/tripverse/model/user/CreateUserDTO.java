package unisinos.tripverse.model.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserDTO {

    private String name;

    private String email;

}
