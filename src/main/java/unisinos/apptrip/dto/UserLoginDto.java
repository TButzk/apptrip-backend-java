package unisinos.apptrip.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserLoginDto {
    private String token;

    private String name;

    private String email;

    private List<UUID> favoritePlacesIds;

    private List<UUID> routeIds;
}

