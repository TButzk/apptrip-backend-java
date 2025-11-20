package unisinos.tripverse.model.route;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Builder
@Data
public class RouteDto {
    private UUID id;	
    private String name;
    private UUID userId;
    private List<UUID> placeIds;
}
