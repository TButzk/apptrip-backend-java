package unisinos.tripverse.model.user;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.place.FavoritePlaces;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class UserDTO {

    private String name;

    private String email;

    private List<UUID> favoritePlacesIds;

    private List<UUID> routeIds;

}
