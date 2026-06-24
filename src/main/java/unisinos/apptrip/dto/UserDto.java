package unisinos.apptrip.dto;

import lombok.*;

import java.util.UUID;
import unisinos.apptrip.model.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private UUID id;

    private String name;

    private String email;

    private UserRole role;
}

