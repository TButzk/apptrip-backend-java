package unisinos.tripverse.model.user;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private UUID id;

    private String name;

    private String email;
}
