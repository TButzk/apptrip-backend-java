package unisinos.tripverse.dto;

import lombok.*;

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
