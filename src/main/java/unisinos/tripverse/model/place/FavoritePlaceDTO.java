package unisinos.tripverse.model.place;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class FavoritePlaceDTO {

    private String name;

    private UUID userId;

    private List<UUID> placesId;
}
